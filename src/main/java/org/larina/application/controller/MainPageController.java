package org.larina.application.controller;

import org.larina.application.entities.Artist;
import org.larina.application.entities.Role;
import org.larina.application.entities.Staff;
import org.larina.application.entities.User;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private ArtistRepo artistRepo;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal User user, Model model){
        Staff staff = staffRepo.findStaffByUserId(user.getId());
        Artist artist = artistRepo.findArtistByUserId(user.getId());
        if(user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("companyMessage", "Admin");
        }else if(staff != null) {
            model.addAttribute("company", staff.getCompany());
        }else{
            model.addAttribute("company", artist.getCompany());
        }
        model.addAttribute("style", true);
        return "/parts/mainUserPage";
    }

}
