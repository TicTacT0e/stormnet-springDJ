package app.dao;

import app.entities.Notification;

import java.util.List;

public interface NotificationDao {

    Notification create();
    Notification findById(int id);
    List<Notification> findAll();
    Notification update();
    Notification delete(int id);
}
