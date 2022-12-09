package org.larina.application.controller;

import org.larina.application.entities.Activity;
import org.larina.application.entities.Artist;
import org.larina.application.entities.Link;
import org.larina.application.repo.ActivityRepo;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.LinkRepo;
import org.larina.application.repo.RewardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ActivityController {
    @Autowired
    private LinkRepo linkRepo;

    @Autowired
    private ActivityRepo activityRepo;
    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private RewardRepo rewardRepo;

    public Activity addActivity(@Valid Activity activity,
                                @Valid Link link,
                                @RequestParam String nickname,
                                BindingResult bindingResult,
                                Model model){
        linkRepo.save(link);
        activity.setLink(link);
        activity.setArtist(artistRepo.findArtistByNickname(nickname));
        activityRepo.save(activity);
        return activity;
    }

    @GetMapping("/activity")
    public String mapActivity(){
        return "activityMenu";
    }
}
