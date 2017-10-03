package com.lifestyle.stps.services;


import com.lifestyle.stps.entities.User;
import java.util.List;

/**
 * Created by User 1 on 26/9/2017.
 */
public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
    User createUser(User user);
    List<?> listAllNonAdmins();
    void deleteUser(Integer id);
}
