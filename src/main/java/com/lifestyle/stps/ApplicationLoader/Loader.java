package com.lifestyle.stps.ApplicationLoader;


import com.lifestyle.stps.Repositories.ProductRepository;
import com.lifestyle.stps.Repositories.TrainingTypeRepository;
import com.lifestyle.stps.entities.Product;
import com.lifestyle.stps.entities.Role;
import com.lifestyle.stps.entities.TrainingType;
import com.lifestyle.stps.entities.User;
import com.lifestyle.stps.services.RoleService;
import com.lifestyle.stps.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User 1 on 20/9/2017.
 */
@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {
    private ProductRepository productRepository;
    private TrainingTypeRepository trainingTypeRepo;
    private UserService userService;
    private RoleService roleService;

    private Logger log = Logger.getLogger(Loader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //For Training Type
    @Autowired
    public void setTrainingTypeRepository(TrainingTypeRepository trainingTypeRepo) {
        this.trainingTypeRepo = trainingTypeRepo;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();
        //loadTrainingType();
    }

    private void loadProducts() {
        Product shirt = new Product();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setProductId("168639393495335947");
        mug.setPrice(new BigDecimal("11.95"));
        productRepository.save(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("user");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("trainer1");
        user3.setPassword("trainer1");
        userService.saveOrUpdate(user3);

    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
        Role trainerRole = new Role();
        trainerRole.setRole("TRAINER");
        roleService.saveOrUpdate(trainerRole);
        log.info("Saved role" + trainerRole.getRole());

    }
    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                    if (user.getUsername().equals("trainer")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    //Locad Training Type
    private void loadTrainingType() {
        TrainingType tt1 = new TrainingType();
        tt1.setType("Testing 1");
        trainingTypeRepo.save(tt1);
        log.info("Saved Training Type - id: " + tt1.getId());
    }
}
