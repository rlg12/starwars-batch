package com.starwarsapi.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by  on 21/07/17.
 */
@Data
@XmlRootElement(name = "people")
public class People {

    private String name;
    private String birthYear;
    private String eyeColor;
    private String gender;
    private String hairColor;
    private String height;
    private String mass;
    private String skinColor;

}
