package com.starwarsapi.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by  on 21/07/17.
 */
@Data
@XmlRootElement(name = "planet")
public class Planet {
    private String name;
    private String diameter;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String gravity;
    private Long population;
    private String climate;
    private String terrain;
    private String surfaceWater;
}
