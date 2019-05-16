package app.dao;

import app.entities.Notification;

import java.util.List;

public interface NotificationDao {

    void create(Notification notification);

    Notification findById(int id);

    List<Notification> findAll();

    void update(Notification notification, int id);

    void delete(int id);
}
