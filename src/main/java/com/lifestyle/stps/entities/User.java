package com.lifestyle.stps.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User 1 on 26/9/2017.
 */

@Entity
public class User extends AbstractDomainClass {
    private String username;
    private int id;
    @Transient
    private String password;

    private String encryptedPassword;
    private Boolean enabled = true;

    private String email;
    private String firstName;
    private String lastName;
    private int day;
    private int month;
    private int year;
    private int phoneNumber;
    private String gender;
    private String country;
    private String homeAddress;
    private int postalCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
    //     inverseJoinColumns = @joinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    private Integer failedLoginAttempts = 0;

    public String getUsername() {
        return username;
    }
    public void setid(int id){this.id= id;}
    public int getid() {return id;}
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail(){ return email;}

    public void setEmail(String email){ this.email = email; }

    public String getFirstName(){ return firstName; }

    public void setFirstName(String firstName){ this.firstName = firstName; }

    public String getLastName(){ return lastName; }

    public void setLastName(String lastName){ this.lastName = lastName; }

    public int getDay(){ return day; }

    public void setDay(int day){ this.day = day; }

    public int getMonth(){ return month; }

    public void setMonth(int month){ this.month = month; }

    public int getYear(){ return year; }

    public void setYear(int year){ this.year = year; }

    public int getPhoneNumber(){ return phoneNumber; }

    public void setPhoneNumber(int phoneNumber){ this.phoneNumber = phoneNumber; }

    public String getGender(){ return gender; }

    public void setGender(String gender){ this.gender = gender; }

    public String getCountry(){ return country; }

    public void setCountry(String country){ this.country = country; }

    public String getHomeAddress(){ return homeAddress; }

    public void setHomeAddress(String homeAddress){ this.homeAddress = homeAddress; }

    public int getPostalCode(){ return postalCode; }

    public void setPostalCode(int postalCode){ this.postalCode = postalCode; }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)){
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
