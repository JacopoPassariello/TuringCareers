package com.turing_careers.data.model;

public class Employer {
    private int id;
    private String firstName;
    private String lastName;
    private String piva;
    private String mail;
    private String password;

    public Employer() {
    }

    public Employer(int id, String firstName, String lastName, String piva, String mail, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.piva = piva;
        this.mail = mail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
