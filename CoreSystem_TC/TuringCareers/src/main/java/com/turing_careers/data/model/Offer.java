package com.turing_careers.data.model;

public class Offer {

    private int id;
    private String title;
    private String description;
    private String state;
    private String locationType;

    public Offer() {
    }

    public Offer(int id, String title, String description, String state, String locationType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.locationType = locationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
}
