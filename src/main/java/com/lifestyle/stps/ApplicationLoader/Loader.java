package com.lifestyle.stps.ApplicationLoader;


import com.lifestyle.stps.Repositories.PersonalCalRepository;
import com.lifestyle.stps.Repositories.ProductRepository;
import com.lifestyle.stps.Repositories.TrainingTypeRepository;

import com.lifestyle.stps.entities.*;

import com.lifestyle.stps.services.RoleService;
import com.lifestyle.stps.services.TrainingTypeService;
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
    private TrainingTypeService trainingTypeService;
    private PersonalCalRepository personalCalRepo;
    private UserService userService;
    private RoleService roleService;

    private Logger log = Logger.getLogger(Loader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //For Training Type
    @Autowired
    public void setTrainingTypeService(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    //For Personal Calendar
    @Autowired
    public void setMyCalRepository(PersonalCalRepository myCalRepos) {
        this.personalCalRepo = myCalRepos;
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
        loadRoles();
        loadUsers();
        assignUsersToUserRole();
        assignUsersToAdminRole();
        assignUsersToTrainerRole();
        loadTrainingType();
        loadSchedule();
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
        user1.setAccountStatus("APPROVED");
        user1.setTrole("TRAINEE");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        user2.setAccountStatus("APPROVED");
        user2.setTrole("ADMIN");
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("trainer1");
        user3.setPassword("trainer1");
        user3.setTrole("TRAINER");
        user3.setAccountStatus("APPROVED");
        Role role = roleService.findByRole("TRAINER");
        user3.addRole(role);
        userService.saveOrUpdate(user3);

        User user4= new User();
        user4.setUsername("mich");
        user4.setPassword("mich");
        user4.setEmail("mich@gmail.com");
        user4.setFirstName("mich");
        user4.setLastName("g");
        user4.setDay(1);
        user4.setMonth(12);
        user4.setPhoneNumber(123456);
        user4.setAccountStatus("APPROVED");
        user4.setGender("Others");
        user4.setCountry("Tiongland");
        user4.setTrole("TRAINEE");
        user4.setHomeAddress("Changchun");
        user4.setPostalCode(123456);
        userService.saveOrUpdate(user4);
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("TRAINEE");
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
                    if (user.getUsername().equals("mich")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignUsersToTrainerRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("TRAINER")) {
                users.forEach(user -> {

                    if (user.getUsername().equals("trainer1")) {
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

    //Load Training Type
    private void loadTrainingType() {
        TrainingType tt1 = new TrainingType();
        tt1.setType("Meditation");
        tt1.setId(1);
        tt1.setName("Yoga");
        tt1.setDetails("Rejuvenate your body with lots of stretching exercise. Keeping your mind in piece");

        tt1.setType("Testing 1");

        TrainingType tt2 = new TrainingType();
        tt2.setName("Jogging");
        tt2.setDetails("Training of stamina and keeping fit.");
        tt2.setType("Cardiology");

        trainingTypeService.saveTrainingType(tt1);
        trainingTypeService.saveTrainingType(tt2);
        log.info("Saved Training Type - id: " + tt1.getId());
        log.info("Saved Training Type - id: " + tt2.getId());
    }

    //Locad Training Type
    private void loadSchedule() {
        PersonalCalendar PC1 = new PersonalCalendar();
        PC1.setUserName("user");
        PC1.setTrainingDateStart("2017-10-28");
        PC1.setTrainingTimeStart("14:00:00");
        PC1.setTrainingType("Testing 1");
        PC1.setTrainingDesc("Test");
        PC1.setTrainingVenue("Test");
       PC1.setTrainingPrice(1211312);


        PersonalCalendar PC2 = new PersonalCalendar();
        PC2.setUserName("admin");
        PC2.setTrainingDateStart("2017-10-29");
        PC2.setTrainingTimeStart("14:00:00");
        PC2.setTrainingType("Testing 1");
        PC2.setTrainingDesc("Test");
        PC2.setTrainingVenue("Test");
        PC2.setTrainingPrice(123123);

        personalCalRepo.save(PC1);
        personalCalRepo.save(PC2);
        log.info("Saved Schedule - id: " + PC1.getId());
        log.info("Saved Schedule - id: " + PC2.getId());
    }



}
