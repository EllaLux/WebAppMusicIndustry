package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Reward {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the rewards result!")
    @Length(max=50, message = "Rewards result is so long!")
    private String result;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    public Reward(){}

    public Reward(String result, Activity activity) {
        this.result = result;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
