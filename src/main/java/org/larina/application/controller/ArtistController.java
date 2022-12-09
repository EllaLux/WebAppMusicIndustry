package org.larina.application.controller;

import org.larina.application.entities.User;
import org.larina.application.entities.UserAndArtist;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/artist")
    public String ArtistList(@RequestParam(required = false) String nickname, Model model){
        Iterable<User> userList = userRepo.findAll();

        List<UserAndArtist> userAndArtistList = null;

        UserAndArtist userAndArtistBD = null;

        List<UserAndArtist> userAndArtistListFilter = null;

        if(nickname != null && !nickname.isEmpty()) {
            userAndArtistListFilter = new ArrayList<UserAndArtist>();
            for (User i : userList) {
                if(artistRepo.findArtistByNicknameAndUserId(nickname, i.getId()) != null) {
                    userAndArtistBD = new UserAndArtist(i, artistRepo.findArtistByNicknameAndUserId(nickname, i.getId()));
                    userAndArtistListFilter.add(userAndArtistBD);
                }
            }
        }

        if(userAndArtistListFilter != null){
            model.addAttribute("nickname",nickname);
            model.addAttribute("userAndArtistList",userAndArtistListFilter);
        } else {
            userAndArtistList = new ArrayList<UserAndArtist>();
            for (User i : userList) {
                if(artistRepo.findArtistByUserId(i.getId()) != null) {
                    userAndArtistBD = new UserAndArtist(i, artistRepo.findArtistByUserId(i.getId()));
                    userAndArtistList.add(userAndArtistBD);
                }
            }
            model.addAttribute("userAndArtistList",userAndArtistList);
        }
        return "artistTable";
    }

}
