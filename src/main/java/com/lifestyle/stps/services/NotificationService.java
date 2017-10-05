package com.lifestyle.stps.services;

import com.lifestyle.stps.entities.Notification;
import org.springframework.stereotype.Service;

/**
 * Created by User 1 on 5/10/2017.
 */
@Service
public interface NotificationService {
    Iterable<Notification> listAllNotifications();
    void deleteNotification(Integer id);
    Notification saveNotification(Notification notification);
    Notification getNotificationByRefId(Integer refId);
    Notification getNotificationById(Integer id);
}
