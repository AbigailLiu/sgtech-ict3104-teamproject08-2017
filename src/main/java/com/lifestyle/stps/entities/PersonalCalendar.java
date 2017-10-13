package com.lifestyle.stps.entities;

//Training Type Model, Done by Mun Han on 30/9/2017

import javax.persistence.*;
import java.util.Date;

@Entity
public class PersonalCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer schedule_id;

    private String user_name;

    private String training_Desc;

    private String training_Type;

    private String training_Date_Start;

    private String training_Time_Start;

    private String training_Time_End;

    private String training_Venue;

    private double training_Price;

    //New Field
    private int training_capactiy;
    private String training_category;
    private String training_status;


    //For ID
    public Integer getId() {
        return schedule_id;
    }
    public void setId(Integer id) {
        this.schedule_id = id;
    }

    //For created by
    public String getUserName() {
        return user_name;
    }
    public void setUserName(String UserName) {
        this.user_name = UserName;
    }

    //For Training Type
    public String getTrainingType() {
        return training_Type;
    }
    public void setTrainingType(String TrainingType) {
        this.training_Type = TrainingType;
    }

    //For Training Description
    public String getTrainingDesc() {
        return training_Desc;
    }
    public void setTrainingDesc(String TrainingDesc) {
        this.training_Desc = TrainingDesc;
    }

    //For Training Date Start
    public String getTrainingDateStart() {
        return training_Date_Start;
    }
    public void setTrainingDateStart(String TrainingDateStart) {
        this.training_Date_Start = TrainingDateStart;
    }

    //For Training Time Start
    public String getTrainingTimeStart() {
        return training_Time_Start;
    }
    public void setTrainingTimeStart(String TrainingTimeStart) {
        this.training_Time_Start = TrainingTimeStart;
    }

    //For Training Time End
    public String getTrainingTimeEnd() {
        return training_Time_End;
    }
    public void setTrainingTimeEnd(String TrainingTimeEnd) {
        this.training_Time_End = TrainingTimeEnd;
    }

    //For Training Venue
    public String getTrainingVenue() {
        return training_Venue;
    }
    public void setTrainingVenue(String TrainingVenue) {
        this.training_Venue = TrainingVenue;
    }

    //For Training Capacity
    public int getTrainingCapacity() {
        return training_capactiy;
    }
    public void setTrainingCapacity(int TrainingCapicity) {
        this.training_capactiy = TrainingCapicity;
    }

    //For Training category
    public String getTrainingCategory() {
        return training_category;
    }
    public void setTrainingCategory(String TrainingCategory) {
        this.training_category = TrainingCategory;
    }

    //For Training Status
    public String getTrainingStatus() {
        return training_status;
    }
    public void setTrainingStatus(String TrainingStatus) {
        this.training_status = TrainingStatus;
    }

    //For Training Price
    public double getTrainingPrice(){return training_Price;}
    public void setTrainingPrice(double Training_Price){this.training_Price = Training_Price;}
}
