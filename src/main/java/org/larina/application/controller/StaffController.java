package org.larina.application.controller;

import org.larina.application.entities.*;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.StaffRepo;
import org.larina.application.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ArtistRepo artistRepo;

    @GetMapping("/staff")
    public String StaffList(@RequestParam(required = false) String fullname,
                            @AuthenticationPrincipal User user, Model model){
        Iterable<User> userList = userRepo.findAll();

        List<UserAndStaff> userAndStaffList = null;

        List<UserAndStaff> userAndStaffListFilter = null;

        UserAndStaff userAndStaffBD = null;
        //попробовать через findByUserName в StaffRepo
        List<User> userListByName = null;

        if(fullname != null && !fullname.isEmpty()) {
            userListByName = new ArrayList<User>();
            for (User i : userList) {
                if(i.getFullname().equals(fullname)) {
                    userListByName.add(i);
                }
            }
            if(!userListByName.isEmpty()) {
                userAndStaffListFilter = new ArrayList<UserAndStaff>();
                for (User j : userListByName) {
                    if(staffRepo.findStaffByUserId(j.getId())!= null || j.getRoles().contains(Role.ADMIN)) {
                        userAndStaffBD = new UserAndStaff(j, staffRepo.findStaffByUserId(j.getId()));
                        userAndStaffListFilter.add(userAndStaffBD);
                    }
                }
            }
        }

        if(userAndStaffListFilter != null){
            model.addAttribute("fullname",fullname);
            model.addAttribute("userAndStaffList",userAndStaffListFilter);
            model.addAttribute("user", user);
        } else {
            userAndStaffList = new ArrayList<UserAndStaff>();
            for (User i : userList) {
                if(staffRepo.findStaffByUserId(i.getId()) != null || i.getRoles().contains(Role.ADMIN)) {
                    userAndStaffBD = new UserAndStaff(i, staffRepo.findStaffByUserId(i.getId()));
                    userAndStaffList.add(userAndStaffBD);
                }
            }
            model.addAttribute("userAndStaffList",userAndStaffList);
        }

        Staff staff = staffRepo.findStaffByUserId(user.getId());
        Artist artist = artistRepo.findArtistByUserId(user.getId());

        if(user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("companyMessage", "Admin");
        }else if(staff != null) {
            model.addAttribute("company", staff.getCompany());
        }else{
            model.addAttribute("company", artist.getCompany());
        }

        return "staffTable";
    }
}
