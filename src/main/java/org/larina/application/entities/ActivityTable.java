package org.larina.application.entities;

public class ActivityTable {
    private Activity activity;

    private Track track;

    private Movie movie;

    private Reward reward;
    private Concert concert;

    private Link link;

    public ActivityTable() {
    }

    public ActivityTable(Activity activity, Track track) {
        this.activity = activity;
        this.track = track;
        this.concert = null;
        this.movie = null;
        this.reward = null;
    }

    public ActivityTable(Activity activity, Movie movie) {
        this.activity = activity;
        this.track = null;
        this.concert = null;
        this.movie = movie;
        this.reward = null;
    }

    public ActivityTable(Activity activity, Reward reward) {
        this.activity = activity;
        this.track = null;
        this.concert = null;
        this.movie = null;
        this.reward = reward;
    }

    public ActivityTable(Activity activity, Concert concert) {
        this.activity = activity;
        this.track = null;
        this.concert = concert;
        this.movie = null;
        this.reward = null;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
