package org.larina.application.entities;

public class UserAndArtist {
    private User user;

    private Artist artist;

    public UserAndArtist(){}

    public UserAndArtist(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
