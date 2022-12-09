package org.larina.application.entities;

public class CompanyAndLink {
    private Company company;
    private Link link;

    public CompanyAndLink(){}

    public CompanyAndLink(Company company, Link link) {
        this.company = company;
        this.link = link;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
