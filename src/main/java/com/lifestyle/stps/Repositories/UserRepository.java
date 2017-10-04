package com.lifestyle.stps.Repositories;


import com.lifestyle.stps.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User 1 on 26/9/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);
}
