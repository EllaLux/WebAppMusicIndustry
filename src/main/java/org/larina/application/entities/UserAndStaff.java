package org.larina.application.entities;

public class UserAndStaff {

    private User user;
    private Staff staff;

    public UserAndStaff(){}

    public UserAndStaff(User user, Staff staff) {
        this.user = user;
        this.staff = staff;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
