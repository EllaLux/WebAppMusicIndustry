package org.larina.application.controller;

import org.larina.application.entities.Artist;
import org.larina.application.entities.Role;
import org.larina.application.entities.Staff;
import org.larina.application.entities.User;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.LinkRepo;
import org.larina.application.repo.StaffRepo;
import org.larina.application.repo.UserRepo;
import org.larina.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private ArtistRepo artistRepo;
    @Autowired
    private UserRepo userRepo;

    //список пользователей
    //ограничиваем доступ к списку пользователей
    //аннотация для каждого метода проверяет у пользователя наличие прав, указанных в скобочках
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Map<String, Object> model){
        model.put("users", userService.findAll());
        return "userList";
    }

    //личная вкладка редактирования пользователя admin
    //из БД
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}") //"{user}" в пути будет через слэш идти идетификатор пользователя
    // @PathVariable получаем пользователя из пути
    public String userEditForm(@PathVariable User user, Map<String, Object> model){
        model.put("user", user);
        //получаем все значения enum (values())
        model.put("roles", Role.values());
        return "userEdit";
    }

    //сохранение отредактированных пользователей admin
    //в БД
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping//userId - по данному параметру мы будем получать пользователя из БД
    //Получаем список полей roles, т.к. переменное количество полей,
    // они все будут попадать в форму, но на сервер будут приходить только те,
    // что отмечены флажком, т.е. в форме будет разное количество полей
    public String userSave(@RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user){
        userService.saveUser(username, form, user);
        return "redirect:/user";
    }

    //пользователя из контекста, чтоб мы получали его из БД
    //просмотр данных пользователя
    @GetMapping("profile")
    public String getProfile(Map<String, Object> model, @AuthenticationPrincipal User user){
        model.put("user", user);
        model.put("staff", staffRepo.findStaffByUserId(user.getId()));
        model.put("password", user.getPassword());
        model.put("contacts", user.getContacts());
        model.put("email", user.getEmail());

        return "profile";
    }

    //сохранение данных пользователя
    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String contacts,
                                @RequestParam String email){
        userService.updateProfile(user, password, contacts, email);
        return "redirect:/user/profile";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @GetMapping("/userDelete")
    public String deleteUser(@RequestParam("userId")
                                Long userId) throws IOException {
        Staff staff = staffRepo.findStaffByUserId(userId);
        Artist artist = artistRepo.findArtistByUserId(userId);
        if(staff!=null) {
            //staffRepo.delete(staff);
            userRepo.delete(staff.getUser());
            return "redirect:/staff";
        }else{
            //artistRepo.delete(artist);
            userRepo.delete(artist.getUser());
            return "redirect:/artist";
        }
    }
}
