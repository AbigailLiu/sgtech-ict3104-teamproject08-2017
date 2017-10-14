package com.lifestyle.stps.Controllers;

import com.lifestyle.stps.algorithms.RandomString;
import com.lifestyle.stps.algorithms.holder.RSEmail;
import com.lifestyle.stps.entities.PersonalCalendar;
import com.lifestyle.stps.entities.Product;
import com.lifestyle.stps.entities.TrainingType;
import com.lifestyle.stps.services.PersonalCalService;
import com.lifestyle.stps.services.ProductService;
import com.lifestyle.stps.services.TrainingTypeService;
import com.lifestyle.stps.services.UserService;
import com.lifestyle.stps.Repositories.NotificationRepository;
import com.lifestyle.stps.entities.*;
import com.lifestyle.stps.services.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Not;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.net.www.content.text.Generic;

import javax.jws.WebParam;
import java.security.SecureRandom;
import java.util.List;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by User 1 on 20/9/2017.
 */
@Controller
public class IndexController {

    private Logger log = Logger.getLogger(IndexController.class);

    private ProductService productService;
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private NotificationService notificationService;

    //Training Type
    private TrainingTypeService TTypeService;

    //Personal Calendar
    private PersonalCalService MyCalService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTrainingTypeService(TrainingTypeService trainingTypeService) {
        this.TTypeService = trainingTypeService;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public void setMyCalService(PersonalCalService myPersonalCalService){
        this.MyCalService = myPersonalCalService;
    }

    public void sendSimpleMail(String inputEmail, String subject, String content) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ict3104scrum@gmail.com");
        message.setTo(inputEmail);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/registercomplete")
    public String registercomplete(){
        return "/registercomplete";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(Model model) {
        User user = new User();
        user.setAccountStatus("PENDING");
        model.addAttribute("user", user);
        return "login";
      //  return "MyPersonalCalendar";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }


    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {

        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    //Add Bu Mun Han
    //For Listing all Training Type
    @RequestMapping(value = "/trainingTypes", method = RequestMethod.GET)
    public String listTT(Model model) {
        model.addAttribute("trainType", TTypeService.listAllTType());
        return "trainingTypeShow";
    }

    //For Add Training Type
    @RequestMapping("trainingType/new")
    public String newTrainingType(Model model){
        model.addAttribute("trainTypeForm", new TrainingType());
        return "addTrainingType";
    }

    //After the users click submit
    @RequestMapping(value = "trainTypeSubmit", method = RequestMethod.POST)
    public String addTrainingType(TrainingType trainingType, final RedirectAttributes redirectAttributes){

        TTypeService.saveTrainingType(trainingType);

        return "redirect:/trainingType/" + trainingType.getId();
    }

    //Get particular training type
    @RequestMapping("trainingType/{id}")
    public String showTrainingType(@PathVariable Integer id, Model model){


        model.addAttribute("trainType", TTypeService.getTrainingTypeID(id));
        return "trainingTypeShow";
    }

    //For Listing all schedule
    @RequestMapping(value = "/traningCalendar", method = RequestMethod.GET)
    public String listPCal(Model model){
        model.addAttribute("myCal", MyCalService.listAllSchedule());
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String name2 = auth2.getName(); //get logged in username
        model.addAttribute("username", name2);
        model.addAttribute("trainType", TTypeService.listAllTType());
        model.addAttribute("scheduleForm", new PersonalCalendar());
        return "MyPersonalCalendar";
    }

    //For Add New Schedule
    @RequestMapping("schedule/new")
    public String newSchedule(Model model){
        model.addAttribute("scheduleForm", new PersonalCalendar());
        model.addAttribute("trainType", TTypeService.listAllTType());
        return "addNewSchedule";
    }

    //After the users click submit
    @RequestMapping(value = "scheduleFormSubmit", method = RequestMethod.POST)
    public String addSchedule(PersonalCalendar PCal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        PCal.setUserName(name);
        MyCalService.saveSchedule(PCal);
        return "redirect:/MyPersonalCalendar/" + PCal.getId();
       // return "redirect:/MyPersonalCalendar/" + "2";
    }

    @RequestMapping("MyPersonalCalendar/{id}")
    public String showSchedule(@PathVariable Integer id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("myCal", MyCalService.listAllSchedule());
        log.info("LIST ALL LA"+ MyCalService.listAllSchedule());
        log.info("MAPLA"+model.asMap());
        Map<String,Object > map = model.asMap();

        // Iterating over keys only
        for (String key : map.keySet()) {
            System.out.println("Key = " + key);
        }
        // Iterating over values only
//        for(int i=0;i<((Collection<Object>)map.values()).size();i++){
//            log.info("i values"+i);
//        for (Object value : map.values()) {
////            Object mapArray[];
////            mapArray = map.values().toArray();
////            System.out.println("MAPARRAY"+  mapArray.toString());
//
////            if ((((ArrayList<PersonalCalendar>) value).get(i).getUserName()) == name) {
//                System.out.println(((ArrayList<PersonalCalendar>) value).get(id-1).getTrainingDateStart());
////            } else {
////                System.out.println("Not matched");
//            }


        model.addAttribute("username", name);
        model.addAttribute("trainType", TTypeService.listAllTType());
        model.addAttribute("scheduleForm", new PersonalCalendar());

        return "MyPersonalCalendar";
    }

    @RequestMapping("user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "usercreateform";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user) {
        userService.createUser(user);
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAllNonAdmins());
        return "userform";
    }

    @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
    String listNotifications(Model model) {
        List<Notification> notifications = (List<Notification>) notificationService.listAllNotifications();
        model.addAttribute("notifications", notifications);
        return "admindashboard";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createNewRegister(User user) {
        try{
            String srole = user.getTrole();
            user.setTrole(srole.toUpperCase());
            Role role = roleService.findByRole(srole.toUpperCase());
            user.addRole(role);
            userService.saveOrUpdate(user);
            Notification notification = new Notification();
            notification.setDescription("Awaiting Account Approval: " + user.getUsername());
            notification.setNotificationType("Account Request");
            notification.setRefId(user.getId());
            notificationService.saveNotification(notification);
            sendSimpleMail(user.getEmail(),"LifeStyle STPS Account Verification", "Thank you for joining STPS. Our administrator will be reviewing your account and get back to you shortly. Thank you for you patience. Cheers!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/registercomplete";
    }

    @RequestMapping(value = "/adminaccountmanagement", method = RequestMethod.GET)
    public String listUser(Model model){
        List<User> users = (List<User>) userService.listAll();
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getAccountStatus().equals("DELETED")){
                users.remove(i);
            }
        }
        if (users.size() == 0){
            users = null;
        }
        model.addAttribute("users",users);
        return "adminaccountmanagement";
    }

    @RequestMapping("accountApproval/{id}")
    public String showNotification(@PathVariable Integer id, Model model) {
        Notification notification = notificationService.getNotificationById(id);
        Integer tid = notification.getRefId();
        if (notification.getNotificationType().equals("Account Request")){
            model.addAttribute("user", userService.getById(tid));
        }else{
            //reserved for training
        }
        return "accountApproval";
    }

    @RequestMapping(value = "account/approve/{id}")
    public String updateApprove(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        user.setAccountStatus("APPROVED");
        userService.saveOrUpdate(user);
        Notification notification = notificationService.getNotificationByRefId(user.getId());
        Integer nid = notification.getId();
        notificationService.deleteNotification(nid);
        return "redirect:/admindashboard";
    }

    @RequestMapping(value = "account/disapprove/{id}")
    public String updateDisapprove(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        user.setAccountStatus("DISAPPROVED");
        userService.saveOrUpdate(user);
        Notification notification = notificationService.getNotificationByRefId(user.getId());
        Integer nid = notification.getId();
        notificationService.deleteNotification(nid);
        return "redirect:/admindashboard";
    }

    @RequestMapping(value = "adminaccountmanagement/delete/{id}")
    public String deleteAccount(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        user.setEnabled(Boolean.FALSE);
        user.setAccountStatus("DELETED");
        userService.saveOrUpdate(user);
        return "redirect:/adminaccountmanagement";
    }

    @RequestMapping(value = "/admintrainingmanagement")
    public String listTraining(Model model){
        model.addAttribute("types", TTypeService.listAllTType());
        return "admintrainingmanagement";
    }

    @RequestMapping(value="trainingtypedelete/{id}")
    public String deleteTrainingType(@PathVariable("id") Integer id){
        TTypeService.deleteTraining(id);
        return "redirect:/admintrainingmanagement";
    }

    @RequestMapping(value="/forgetpassword")
    public String redirectForget(Model model){
        RSEmail rsEmail = new RSEmail();
        model.addAttribute("model", rsEmail);
        return "forgetpassword";
    }

    @RequestMapping(value = "/sendReset", method = RequestMethod.POST)
    public String sendReset(RSEmail rsEmail){

        try{
            User user = userService.findByEmail(rsEmail.getEmail());
            String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
            RandomString tickets = new RandomString(23, new SecureRandom(), easy);
            String password = tickets.nextString();
            user.setPassword(password);
            userService.saveOrUpdate(user);
            sendSimpleMail(user.getEmail(),"LifeStyle STPS Account Password Reset", "The password for the account with the username, "+ user.getUsername()+" has been reset.\nYour temporary password is "+password+" \nPlease change your password after logging in. \nCheers!\n\n LifeStyle STPS Administration Team");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}
