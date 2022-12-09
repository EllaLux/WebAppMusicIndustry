package org.larina.application.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the link!")
    @Length(max=2048, message = "Link is so long!")
    private String site;

    public Link() {}

    public Link(Long id, String site) {
        this.id = id;
        this.site = site;
    }

    public Link(Link link) {
        this.id = link.getId();
        this.site = link.getSite();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
