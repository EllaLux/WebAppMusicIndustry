package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the activity title!")
    @Length(max=250, message = "Activity title is so long!")
    private String title;

    @NotBlank(message = "Please, fill the date of the event!")
    @Length(max=50, message = "Date is so long!")
    private String dateEvent;

    @NotNull(message = "Please, fill the expenses")
    private Long expenses;

    @NotNull(message = "Please, fill the income")
    private Long income;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artist artist;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "link_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Link link;

    public Activity() {}

    public Activity(String title, String dateEvent, Long expenses, Long income, Artist artist, Link link) {
        this.title = title;
        this.dateEvent = dateEvent;
        this.expenses = expenses;
        this.income = income;
        this.artist = artist;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Long getExpenses() {
        return expenses;
    }

    public void setExpenses(Long expenses) {
        this.expenses = expenses;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
