package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the artist birthday!")
    @Length(max=50, message = "Artist birthday is so long!")
    private String birthday;

    @NotBlank(message = "Please, fill the artist education!")
    @Length(max=250, message = "Artist education is so long!")
    private String education;

    @NotBlank(message = "Please, fill the artist date debut!")
    @Length(max=50, message = "Date debut is so long!")
    private String debut;

    @NotBlank(message = "Please, fill the artist nickname!")
    @Length(max=250, message = "Nickname is so long!")
    private String nickname;

    @NotNull(message = "Please, fill the artist salary")
    private Long salary;

    @NotBlank(message = "Please, fill the artist position in the group!")
    @Length(max=100, message = "Position in the group is so long!")
    private String positionGroup;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

    public Artist() {}

    public Artist(String birthday,
                  String education,
                  String debut,
                  String nickname,
                  Long salary,
                  String positionGroup,
                  User user,
                  Company company) {
        this.birthday = birthday;
        this.education = education;
        this.debut = debut;
        this.nickname = nickname;
        this.salary = salary;
        this.positionGroup = positionGroup;
        this.user = user;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getPositionGroup() {
        return positionGroup;
    }

    public void setPositionGroup(String positionGroup) {
        this.positionGroup = positionGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
