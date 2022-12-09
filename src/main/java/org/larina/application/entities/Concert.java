package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Concert {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the concert location!")
    @Length(max=50, message = "Concert location is so long!")
    private String location;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    public Concert() {
    }

    public Concert(String location, Activity activity) {
        this.location = location;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
