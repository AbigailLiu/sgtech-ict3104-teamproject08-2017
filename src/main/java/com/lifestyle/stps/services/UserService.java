package com.lifestyle.stps.services;


import com.lifestyle.stps.entities.User;

/**
 * Created by User 1 on 26/9/2017.
 */
public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
}
