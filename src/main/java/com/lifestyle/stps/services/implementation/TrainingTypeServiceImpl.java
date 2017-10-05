package com.lifestyle.stps.services.implementation;

import com.lifestyle.stps.Repositories.TrainingTypeRepository;
import com.lifestyle.stps.entities.TrainingType;
import com.lifestyle.stps.services.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {
    private TrainingTypeRepository trainingtRepo;

    @Autowired
    public void setTrainingRepo(TrainingTypeRepository ttRepo) {
        this.trainingtRepo = ttRepo;
    }

    @Override
    public Iterable<TrainingType> listAllTType() {
        return trainingtRepo.findAll();
    }

    @Override
    public TrainingType getTrainingTypeID(Integer id) {
        return trainingtRepo.findOne(id);
    }

    @Override
    public TrainingType saveTrainingType(TrainingType tt) {
        return trainingtRepo.save(tt);
    }

}
