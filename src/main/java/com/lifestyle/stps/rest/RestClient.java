package com.lifestyle.stps.rest;

import com.lifestyle.stps.entities.User;
import com.lifestyle.stps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

/**
 * Created by User 1 on 4/10/2017.
 */

@Service
@Path("/rest")
public class RestClient {

    @Autowired
    private UserService userService;

    @GET
    @Path("/register/checkusername/{username}")
    public Response checkUsername(@PathParam("username") String username){
//        System.out.println(username);
        User user  = userService.findByUsername(username.toLowerCase());
        if (user == null){
            return Response.status(201).entity("pass").build();
        }else{
            return Response.status(201).entity("fail").build();
        }
    }
}
