package com.lifestyle.stps.Configurations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by User 1 on 4/10/2017.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.lifestyle.stps.entities"})
@EnableJpaRepositories(basePackages = {"com.lifestyle.stps.Repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
