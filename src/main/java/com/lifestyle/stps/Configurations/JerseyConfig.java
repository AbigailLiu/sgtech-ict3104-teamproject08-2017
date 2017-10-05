package com.lifestyle.stps.Configurations;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * Created by User 1 on 4/10/2017.
 */

@Configuration
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig(){
        register(RequestContextFilter.class);
        packages("com.lifestyle.stps");
        register(LoggingFeature.class);
    }
}
