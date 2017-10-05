package com.lifestyle.stps.entities;

//Training Type Model, Done by Mun Han on 30/9/2017

import javax.persistence.*;

@Entity
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    private String details;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDetails(String details) {this.details = details;}

    public String getDetails() {return details;}

    public void setName(String name) {this.name=name;}

    public String getName(){return name;}
}
