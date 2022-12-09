package org.larina.application.controller;

import org.larina.application.entities.*;
import org.larina.application.repo.ActivityRepo;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.ConcertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConcertController {
    @Autowired
    private ActivityController activityController;
    @Autowired
    private ConcertRepo concertRepo;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @GetMapping("/concert")
    public String ConcertList(@RequestParam(required = false) String filter,
                              Model model){

        Artist artist = null;
        List<ActivityTable> activityConcertList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        if (filter != null && !filter.isEmpty()) {
            artist = artistRepo.findArtistByNickname(filter);
            if (artist != null) {
                activitiesFindId = activityRepo.findByArtistId(artist.getId());
                if (activitiesFindId != null) {
                    activityConcertList = new ArrayList<ActivityTable>();
                    for (Activity a : activitiesFindId) {
                        if (a != null) {
                            Concert concert = concertRepo.findConcertByActivityId(a.getId());
                            if (concert != null) {
                                activityTableObj = new ActivityTable(a, concert);
                                activityConcertList.add(activityTableObj);
                            }
                        }
                    }
                }
            }
        }
        if(activityConcertList != null){
            model.addAttribute("activityConcertList",activityConcertList);
            model.addAttribute("filter", filter);
            model.addAttribute("artistNickname", artist.getNickname());
        }
        else{
            model.addAttribute("activityConcertList",null);
            model.addAttribute("artistNickname", "Введите никнейм!");
        }

        return "concertTable";
    }

    @PostMapping("/concert")
    public String addConcert(@Valid Activity activity,
                             @Valid Link link,
                             @RequestParam String nickname,
                             @Valid Concert concert,
                             BindingResult bindingResult,
                             Model model){
        List<ActivityTable> activityConcertList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        concert.setActivity(activityController.addActivity(activity, link, nickname, bindingResult, model));
        concertRepo.save(concert);

        Artist artist = artistRepo.findArtistByNickname(nickname);
        if (artist != null) {
            activitiesFindId= activityRepo.findByArtistId(artist.getId());
            if (activitiesFindId != null) {
                activityConcertList = new ArrayList<ActivityTable>();
                for(Activity a : activitiesFindId) {
                    concert = concertRepo.findConcertByActivityId(a.getId());
                    if (concert != null) {
                        activityTableObj = new ActivityTable(a, concert);
                        activityConcertList.add(activityTableObj);
                    }
                }
            }
        }
        if(activityConcertList != null){
            model.addAttribute("activityConcertList",activityConcertList);
            model.addAttribute("artistNickname", artist.getNickname());
        }

        return "concertTable";
    }
}
