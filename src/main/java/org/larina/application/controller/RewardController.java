package org.larina.application.controller;

import org.larina.application.entities.*;
import org.larina.application.repo.ActivityRepo;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.RewardRepo;
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
public class RewardController {
    @Autowired
    private ActivityController activityController;
    @Autowired
    private RewardRepo rewardRepo;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @GetMapping("/reward")
    public String RewardList(@RequestParam(required = false) String filter,
                             Model model){

        Artist artist = null;
        List<ActivityTable> activityRewardList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        if (filter != null && !filter.isEmpty()) {
            artist = artistRepo.findArtistByNickname(filter);
            if (artist != null) {
                activitiesFindId = activityRepo.findByArtistId(artist.getId());
                if (activitiesFindId != null) {
                    activityRewardList = new ArrayList<ActivityTable>();
                    for (Activity a : activitiesFindId) {
                        if (a != null) {
                            Reward reward = rewardRepo.findRewardByActivityId(a.getId());
                            if (reward != null) {
                                activityTableObj = new ActivityTable(a, reward);
                                activityRewardList.add(activityTableObj);
                            }
                        }
                    }
                }
            }
        }
        if(activityRewardList != null){
            model.addAttribute("activityRewardList",activityRewardList);
            model.addAttribute("filter", filter);
            model.addAttribute("artistNickname", artist.getNickname());
        }
        else{
            model.addAttribute("activityRewardList",null);
            model.addAttribute("artistNickname", "Введите никнейм!");
        }
        return "rewardTable";
    }

    @PostMapping("/reward")
    public String addReward(@Valid Activity activity,
                            @Valid Link link,
                            @RequestParam String nickname,
                            @Valid Reward reward,
                            BindingResult bindingResult,
                            Model model){
        List<ActivityTable> activityRewardList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        reward.setActivity(activityController.addActivity(activity, link, nickname, bindingResult, model));
        rewardRepo.save(reward);

        Artist artist = artistRepo.findArtistByNickname(nickname);
        if (artist != null) {
            activitiesFindId= activityRepo.findByArtistId(artist.getId());
            if (activitiesFindId != null) {
                activityRewardList = new ArrayList<ActivityTable>();
                for(Activity a : activitiesFindId) {
                    reward = rewardRepo.findRewardByActivityId(a.getId());
                    if (reward != null) {
                        activityTableObj = new ActivityTable(a, reward);
                        activityRewardList.add(activityTableObj);
                    }
                }
            }
        }
        if(activityRewardList != null){
            model.addAttribute("activityRewardList",activityRewardList);
            model.addAttribute("artistNickname", artist.getNickname());
        }

        return "rewardTable";
    }
}
