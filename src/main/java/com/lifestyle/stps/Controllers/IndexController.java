package com.lifestyle.stps.Controllers;

import com.lifestyle.stps.entities.Product;
import com.lifestyle.stps.entities.TrainingType;
import com.lifestyle.stps.entities.User;
import com.lifestyle.stps.services.ProductService;
import com.lifestyle.stps.services.TrainingTypeService;
import com.lifestyle.stps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private UserService userService;

    //Training Type
    private TrainingTypeService TTypeService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setTrainingTypeService(TrainingTypeService trainingTypeService){
        this.TTypeService = trainingTypeService;
    }
//JAVA EMAIL
//    @Autowired
//    private JavaMailSender mailSender;
//    public void sendSimpleMail(String inputEmail) throws Exception {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("ict3104scrum@gmail.com");
//        message.setTo(inputEmail);
//        message.setSubject(" CONGRATULATIONS ");
//        message.setText(" you are registered! ");
//        mailSender.send(message);
//    }
    @RequestMapping("/")
    String index(){
//        try {
//            sendSimpleMail("micgohsl94@gmail.com");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "userregisteredemail";
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

    //For Listing all Training Type
    @RequestMapping(value = "/trainingTypes", method = RequestMethod.GET)
    public String listTT(Model model){
        model.addAttribute("trainType", TTypeService.listAllTType());
        return "trainingTypeShow";
    }

    //For Add Training Type
    @RequestMapping("trainingType/new")
    public String newTrainingType(Model model){
        model.addAttribute("trainType", new TrainingType());
        return "addTrainingType";
    }

    @RequestMapping(value = "trainType", method = RequestMethod.POST)
    public String addTrainingType(TrainingType trainingType){

        TTypeService.saveTrainingType(trainingType);

        return "redirect:/trainType/" + trainingType.getId();
    }

    @RequestMapping("trainType/{id}")
    public String showTrainingType(@PathVariable Integer id, Model model){
        model.addAttribute("trainType", TTypeService.getTrainingTypeID(id));
        return "trainingTypeShow";
    }




    @RequestMapping("user/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "usercreateform";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user){
        userService.createUser(user);
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("users", userService.listAllNonAdmins());
        return "userform";
    }


}
