package com.lifestyle.stps.services.implementation;

import com.lifestyle.stps.Repositories.PersonalCalRepository;
import com.lifestyle.stps.entities.PersonalCalendar;
import com.lifestyle.stps.services.PersonalCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalCalServiceImpl implements PersonalCalService {
    private PersonalCalRepository pCalRepo;

    @Autowired
    public void setMyCalRepo(PersonalCalRepository pCalRepos) {
        this.pCalRepo = pCalRepos;
    }

    @Override
    public Iterable<PersonalCalendar> listAllSchedule() {
        return pCalRepo.findAll();
    }

    @Override
    public PersonalCalendar getScheduleID(Integer id) {
        return pCalRepo.findOne(id);
    }

    @Override
    public PersonalCalendar saveSchedule(PersonalCalendar tt) {
        return pCalRepo.save(tt);
    }

}
