package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the producer name!")
    @Length(max=250, message = "Producer name is so long!")
    private String producer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    public Movie(){}

    public Movie(String producer, Activity activity) {
        this.producer = producer;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
