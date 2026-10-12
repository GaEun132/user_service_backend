package com.example.petlog.repository;

import com.example.petlog.entity.Pet;
import com.example.petlog.entity.User;
import com.example.petlog.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

    List<UserNotification> findAllByReceiver(User user);
    @Query("SELECT un FROM UserNotification un join fetch un.notification WHERE un.receiver = :user ORDER BY un.createdAt DESC")
    List<UserNotification> findAllByReceiverOrderByCreatedAtDesc(User user);
}
