package com.lifestyle.stps.services.implementation;

import com.lifestyle.stps.Repositories.NotificationRepository;
import com.lifestyle.stps.entities.Notification;
import com.lifestyle.stps.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User 1 on 5/10/2017.
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;

    @Autowired
    private void setNotificationRepository(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public Iterable<Notification> listAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void deleteNotification(Integer id) {
        notificationRepository.delete(id);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationByRefId(Integer refId) {
        return notificationRepository.findByRefId(refId);
    }

    @Override
    public Notification getNotificationById(Integer id) {
        return notificationRepository.findOne(id);
    }
}
