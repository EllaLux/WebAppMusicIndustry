package org.larina.application.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please, fill the company name!")
    @Length(max=50, message = "Company name is so long!")
    private String name;
    @NotBlank(message = "Please, fill the date of foundation!")
    @Length(max=10, message = "Date of foundation is not correctly!")
    private String date;//dateOfFoundation
    @NotBlank(message = "Please, fill the address!")
    @Length(max=250, message = "Address is so short or long!")
    private String address;
    @NotNull(message = "Please, fill the budget")
    private Long budget;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "link_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Link link;

    public Company(){}

    public Company(String name, String date, String address, Long budget, Link link) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.budget = budget;
        this.link = link;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
