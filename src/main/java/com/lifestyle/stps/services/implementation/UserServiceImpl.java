package com.lifestyle.stps.services.implementation;

import com.lifestyle.stps.Repositories.UserRepository;
import com.lifestyle.stps.entities.User;
import com.lifestyle.stps.services.EncryptionService;
import com.lifestyle.stps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User 1 on 26/9/2017.
 */
@Service
@Profile("springdatajpa")
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        return userRepository.save(domainObject);
    }
    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user){
        user.setEnabled(false);
        if(user.getPassword() != null){
            user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public List<?> listAllNonAdmins() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        for(User user:users){
            if(user.getRoles().contains("ADMIN")){
                users.remove(user);
            }
        }
        return users;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id){
        userRepository.delete(id);
    }

    

}
