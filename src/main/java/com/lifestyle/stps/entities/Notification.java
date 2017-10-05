package com.lifestyle.stps.entities;

import javax.persistence.Entity;

/**
 * Created by User 1 on 5/10/2017.
 */
@Entity
public class Notification extends AbstractDomainClass{

    Integer refId;
    String notificationType;
    String description;

    public String getNotificationType(){
        return this.notificationType;
    }

    public void setNotificationType(String notificationType){
        this.notificationType = notificationType;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setRefId(Integer refId){
        this.refId = refId;
    }

    public Integer getRefId(){
        return this.refId;
    }
}
