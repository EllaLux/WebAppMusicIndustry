package org.larina.application.controller;

import org.larina.application.entities.*;
import org.larina.application.repo.ActivityRepo;
import org.larina.application.repo.ArtistRepo;
import org.larina.application.repo.MovieRepo;
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
public class MovieController {
    @Autowired
    private ActivityController activityController;
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ActivityRepo activityRepo;

    @GetMapping("/movie")
    public String MovieList(@RequestParam(required = false) String filter,
                            Model model){
        Artist artist = null;
        List<ActivityTable> activityMovieList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        if (filter != null && !filter.isEmpty()) {
            artist = artistRepo.findArtistByNickname(filter);
            if (artist != null) {
                activitiesFindId = activityRepo.findByArtistId(artist.getId());
                if (activitiesFindId != null) {
                    activityMovieList = new ArrayList<ActivityTable>();
                    for (Activity a : activitiesFindId) {
                        if (a != null) {
                            Movie movie = movieRepo.findMovieByActivityId(a.getId());
                            if (movie != null) {
                                activityTableObj = new ActivityTable(a, movie);
                                activityMovieList.add(activityTableObj);
                            }
                        }
                    }
                }
            }
        }
        if(activityMovieList != null){
            model.addAttribute("activityMovieList",activityMovieList);
            model.addAttribute("filter", filter);
            model.addAttribute("artistNickname", artist.getNickname());
        }
        else{
            model.addAttribute("activityMovieList",null);
            model.addAttribute("artistNickname", "Введите никнейм!");
        }
        return "movieTable";
    }

    @PostMapping("/movie")
    public String addMovie(@Valid Activity activity,
                           @Valid Link link,
                           @RequestParam String nickname,
                           @Valid Movie movie,
                           BindingResult bindingResult,
                           Model model){
        List<ActivityTable> activityMovieList = null;
        ActivityTable activityTableObj = null;
        List<Activity> activitiesFindId = null;

        movie.setActivity(activityController.addActivity(activity, link, nickname, bindingResult, model));
        movieRepo.save(movie);

        Artist artist = artistRepo.findArtistByNickname(nickname);
        if (artist != null) {
            activitiesFindId= activityRepo.findByArtistId(artist.getId());
            if (activitiesFindId != null) {
                activityMovieList = new ArrayList<ActivityTable>();
                for(Activity a : activitiesFindId) {
                    movie = movieRepo.findMovieByActivityId(a.getId());
                    if (movie != null) {
                        activityTableObj = new ActivityTable(a, movie);
                        activityMovieList.add(activityTableObj);
                    }
                }
            }
        }
        if(activityMovieList != null){
            model.addAttribute("activityMovieList",activityMovieList);
            model.addAttribute("artistNickname", artist.getNickname());
        }

        return "movieTable";
    }
}
