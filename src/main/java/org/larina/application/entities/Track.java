package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Track {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the track name!")
    @Length(max=250, message = "Track name is so long!")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    public Track() {
    }

    public Track(String name, Activity activity) {
        this.name = name;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
