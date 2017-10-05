package com.lifestyle.stps.services;

import com.lifestyle.stps.entities.TrainingType;
import org.springframework.stereotype.Service;

@Service
public interface TrainingTypeService {

    Iterable<TrainingType> listAllTType();

    TrainingType getTrainingTypeID(Integer id);

    TrainingType saveTrainingType(TrainingType trainingType);
}
