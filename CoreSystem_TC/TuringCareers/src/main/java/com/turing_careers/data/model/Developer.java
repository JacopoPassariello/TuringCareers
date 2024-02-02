package com.turing_careers.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
    private int id;
    private String firstName;
    private String lastName;
    private String bio;
    private String mail;
    private String password;
}
