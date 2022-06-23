package com.moringaschool.lend_a_hand.Model;

public class Organizations {
    private String name;
    private String contact;
    private String openHours;
    private String description;

    // empty constructor for firebase instance
    public Organizations(){}

    // class constructor
    public Organizations(String name, String contact, String openHours, String description) {
        this.name = name;
        this.contact = contact;
        this.openHours = openHours;
        this.description = description;
    }

    // Getter Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
