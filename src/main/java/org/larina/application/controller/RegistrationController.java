package org.larina.application.controller;

import org.larina.application.entities.Artist;
import org.larina.application.entities.Staff;
import org.larina.application.entities.User;
import org.larina.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/staffRegistration")
    public String registrationStaff(){
        return "staffRegistration";
    }

    @PostMapping("/staffRegistration")
    public String addUserStaff(@RequestParam("password2") String passwordConfirm,
                               @RequestParam String companyName,
                               @RequestParam("file") MultipartFile file,
                               @Valid Staff staff,
                               @Valid User user,
                               BindingResult bindingResult,
                               Model model) throws IOException {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean differentPassword = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        registrationValid(isConfirmEmpty, differentPassword, model);

        if(isConfirmEmpty || bindingResult.hasErrors() || differentPassword){
            Map<String,String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user", user);
            return "staffRegistration";
        }

        boolean check = false;

        if(userService.addUser(user,companyName, staff, check, file, null) == 0){
            model.addAttribute("usernameError", "Пользователь существует!");
            return "staffRegistration";
        }else if(userService.addUser(user,companyName, staff, check, file, null) == 1) {
            model.addAttribute("companyNameError", "Такой компании не существует!");
            return "staffRegistration";
        }

        return "redirect:/login";
    }

    @GetMapping("/artistRegistration")
    public String registrationArtist(){
        return "artistRegistration";
    }

    @PostMapping("/artistRegistration")
    public String addUserArtist(@RequestParam("password2") String passwordConfirm,
                               @RequestParam String companyName,
                               @RequestParam("file") MultipartFile file,
                                Artist artist,
                               @Valid User user,
                               BindingResult bindingResult,
                               Model model) throws IOException {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean differentPassword = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        registrationValid(isConfirmEmpty, differentPassword, model);

        if(isConfirmEmpty || bindingResult.hasErrors() || differentPassword){
            Map<String,String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user", user);
            return "artistRegistration";
        }

        boolean check = true;

        if(userService.addUser(user,companyName, null, check, file, artist) == 0){
            model.addAttribute("usernameError", "Пользователь существует!");
            return "artistRegistration";
        }else if(userService.addUser(user,companyName,null, check, file, artist) == 1) {
            model.addAttribute("companyNameError", "Такой компании не существует!");
            return "artistRegistration";
        }

        return "redirect:/login";
    }

    public Void registrationValid(boolean isConfirmEmpty,
                                  boolean differentPassword,
                                  Model model){
        if(isConfirmEmpty){
            model.addAttribute("password2Error", "Duplicate password is empty!");
        }
        //сверяем пароли
        if(differentPassword){
            model.addAttribute("passwordError", "Password are different!");
        }
        return null;
    }

    //обрабатываем подтверждение аккаунта
    //пользователь будет переходить по ссылке и письма
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Successfully activated");
        }else{
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found");
        }

        return "login";
    }
}
