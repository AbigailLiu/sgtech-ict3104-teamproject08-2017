package com.lifestyle.stps.Controllers;

import com.lifestyle.stps.entities.PersonalCalendar;
import com.lifestyle.stps.entities.Product;
import com.lifestyle.stps.entities.TrainingType;
import com.lifestyle.stps.services.PersonalCalService;
import com.lifestyle.stps.services.ProductService;
import com.lifestyle.stps.services.TrainingTypeService;
import com.lifestyle.stps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by User 1 on 20/9/2017.
 */
@Controller
public class IndexController {

    private ProductService productService;

    //Training Type
    private TrainingTypeService TTypeService;

    //Personal Calendar
    private PersonalCalService MyCalService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @Autowired
    public void setTrainingTypeService(TrainingTypeService trainingTypeService){
        this.TTypeService = trainingTypeService;
    }

    @Autowired
    public void setMyCalService(PersonalCalService myPersonalCalService){
        this.MyCalService = myPersonalCalService;
    }


    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }



    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){

        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    //Add Bu Mun Han
    //For Listing all Training Type
    @RequestMapping(value = "/trainingTypes", method = RequestMethod.GET)
    public String listTT(Model model){
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
    public String addTrainingType(TrainingType trainingType){

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
    }

    @RequestMapping("MyPersonalCalendar/{id}")
    public String showSchedule(@PathVariable Integer id, Model model){
        model.addAttribute("myCal", MyCalService.listAllSchedule());
        return "MyPersonalCalendar";
    }



}
