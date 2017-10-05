package com.lifestyle.stps.Repositories;

import com.lifestyle.stps.entities.Notification;
import com.lifestyle.stps.services.CRUDService;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User 1 on 5/10/2017.
 */
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    Notification findByRefId(Integer refId);
}
