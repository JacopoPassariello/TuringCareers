package com.turing_careers.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    private int id;
    private String title;
    private String description;
    private String state;
    private String locationType;
}
