package com.turing_careers.data.model;

public class Location {
    private int id;
    private String name;
    private String lat;
    private String log;

    public Location() {
    }

    public Location(int id, String name, String lat, String log) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.log = log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
