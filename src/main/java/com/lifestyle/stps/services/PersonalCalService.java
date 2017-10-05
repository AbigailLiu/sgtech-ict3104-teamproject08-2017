package com.lifestyle.stps.services;

import com.lifestyle.stps.entities.PersonalCalendar;
import org.springframework.stereotype.Service;

@Service
public interface PersonalCalService {

    Iterable<PersonalCalendar> listAllSchedule();

    PersonalCalendar getScheduleID(Integer id);

    PersonalCalendar saveSchedule(PersonalCalendar pCalendar);
}
