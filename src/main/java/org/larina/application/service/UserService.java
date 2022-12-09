package org.larina.application.service;

import org.larina.application.entities.*;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.CompanyRepo;
import org.larina.application.repo.StaffRepo;
import org.larina.application.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ArtistRepo artistRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${upload.path}")//указываем спрингу, что хотим получить upload.path переменную
    private String uploadPath;//вставляем значение в переменную

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        if(user.getActivationCode() != null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    //регистрация пользователя
    public Integer addUser(User user,
                           String companyName,
                           Staff staff,
                           boolean check,
                           MultipartFile file,
                           Artist artist) throws IOException {
        User userBD = userRepo.findByUsername(user.getUsername());

        if (userBD != null) {
            return 0;
        }

        List<Company> companyList = companyRepo.findAll();

        for (Company i : companyList) {
            if (i.getName().equals(companyName)) {
                if (file != null && !file.getOriginalFilename().isEmpty()) {
                    File uploadDir = new File(uploadPath);
                    //проверяем существует ли у нас директория для сохранения файла
                    if (!uploadDir.exists()) {//если не существует - создаем её
                        uploadDir.mkdir();
                    }
                    //создадим уникальное имя файла
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + file.getOriginalFilename();
                    //загружаем файл
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                    user.setFilename(resultFilename);
                }
                user.setActive(true);
                user.setRoles(Collections.singleton(Role.USER));//одно единственное значение
                //генерируем уникальный код
                user.setActivationCode(UUID.randomUUID().toString());
                //почта подтверждена, как только пользователь перешел по ссылке с таким кодом
                //шифрование пароля
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                if(check){
                    artist.setUser(user);
                    artist.setCompany(i);
                    artistRepo.save(artist);
                }else {
                    staff.setUser(user);
                    staff.setCompany(i);
                    staffRepo.save(staff);
                }
                sendMessage(user);

                return 2;//возвращение на страницу логин
            }
        }

        return 1;
    }

    private void sendMessage(User user) {
        //отправляем пользователю оповещение
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "To enter the \"Pop Stars\" database, " +
                            "you need to follow the link below: \n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailService.send(user.getEmail(), "Activation", message);
        }
    }

    //активация пользователя
    public boolean activateUser(String code) {
        //ищет пользователя по коду активации в репозитории
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            //активация не удалась
            return false;
        }

        //пользователь подтвердил свой почтовый ящик
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    //список пользователей
    public List<User> findAll() {
        return userRepo.findAll();
    }

    //логика сохранение отредактированных пользователей admin
    public void saveUser(String username, Map<String, String> form, User user) {
        user.setUsername(username);

        //прежде чем менять роли пользователя, нужно получить список этих ролей, чтобы проверить,
        // что они установлены данному пользователю
        //роли нужно перевести из enum в строковый вид и поместить в список
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name).
                collect(Collectors.toSet());

        //очищаем роли, для того, чтобы добавить
        user.getRoles().clear();

        //проверяем, что форма содержит роли для нашего пользователя
        //если у нас в форме присутствует роль, значит в интерфейсе для неё установлен флажок
        //помимо ролей у нас присутствуют ненужные нам части(iD, токены...)
        for (String key : form.keySet()) {
            //поэтому тут мы должны отфильтровать список ролей
            //проверяем, что роли содержат такой ключ
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }

        }

        userRepo.save(user);
    }

    //сохранение обновленного профиля пользователя
    public void updateProfile(User user, String password, String contacts, String email) {
        String userEmail = user.getEmail();
        //проверим, изменился ли email
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        //если email изменился
        if (isEmailChanged){
            user.setEmail(email);

            //если обновили email нужно отправить новый код, а перед этим сгенерировать его
            //проверяем установил ли пользователь новый email
            if (!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if(!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }

        if(!StringUtils.isEmpty(contacts)){
            user.setContacts(contacts);
        }

        userRepo.save(user);
        sendMessage(user);
    }
}
