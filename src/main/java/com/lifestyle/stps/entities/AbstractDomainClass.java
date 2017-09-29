package com.lifestyle.stps.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User 1 on 26/9/2017.
 */

@MappedSuperclass
public class AbstractDomainClass implements DomainObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion(){
        return this.version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }
}
