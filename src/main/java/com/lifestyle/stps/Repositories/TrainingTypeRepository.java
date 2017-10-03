package com.lifestyle.stps.Repositories;


import com.lifestyle.stps.entities.Product;
import com.lifestyle.stps.entities.TrainingType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User 1 on 20/9/2017.
 */
public interface TrainingTypeRepository extends CrudRepository<TrainingType, Integer> {
}