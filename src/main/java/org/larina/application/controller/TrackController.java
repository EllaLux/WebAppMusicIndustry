package org.larina.application.controller;

import org.larina.application.entities.*;
import org.larina.application.repo.ActivityRepo;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.TrackRepo;
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
public class TrackController {

    @Autowired
    private ActivityController activityController;
    @Autowired
    private TrackRepo trackRepo;
    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @GetMapping("/track")
    public String TrackList(@RequestParam(required = false) String filter,
                            Model model) {
        Artist artist = null;
        List<ActivityTable> activityTrackList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        if (filter != null && !filter.isEmpty()) {
            artist = artistRepo.findArtistByNickname(filter);
            if (artist != null) {
                activitiesFindId = activityRepo.findByArtistId(artist.getId());
                if (activitiesFindId != null) {
                    activityTrackList = new ArrayList<ActivityTable>();
                    for (Activity a : activitiesFindId) {
                        if (a != null) {
                            Track track = trackRepo.findTrackByActivityId(a.getId());
                            if (track != null) {
                                activityTableObj = new ActivityTable(a, track);
                                activityTrackList.add(activityTableObj);
                            }
                        }
                    }
                }
            }
        }
        if(activityTrackList != null){
            model.addAttribute("activityTrackList",activityTrackList);
            model.addAttribute("filter", filter);
            model.addAttribute("artistNickname", artist.getNickname());
        }
        else{
            model.addAttribute("activityTrackList",null);
            model.addAttribute("artistNickname", "Введите никнейм!");
        }
        return "trackTable";
    }

    @PostMapping("/track")
    public String addTrack(@Valid Activity activity,
                           @Valid Link link,
                           @RequestParam String nickname,
                           @Valid Track track,
                           BindingResult bindingResult,
                           Model model){
        List<ActivityTable> activityTrackList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        track.setActivity(activityController.addActivity(activity, link, nickname, bindingResult, model));
        trackRepo.save(track);

        Artist artist = artistRepo.findArtistByNickname(nickname);
        if (artist != null) {
            activitiesFindId= activityRepo.findByArtistId(artist.getId());
            if (activitiesFindId != null) {
                activityTrackList = new ArrayList<ActivityTable>();
                for(Activity a : activitiesFindId) {
                    track = trackRepo.findTrackByActivityId(a.getId());
                    if (track != null) {
                        activityTableObj = new ActivityTable(a, track);
                        activityTrackList.add(activityTableObj);
                    }
                }
            }
        }
        if(activityTrackList != null){
            model.addAttribute("activityTrackList",activityTrackList);
            model.addAttribute("artistNickname", artist.getNickname());
        }
        return "trackTable";
    }
}
