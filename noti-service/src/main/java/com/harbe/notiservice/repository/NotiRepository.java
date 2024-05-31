package com.harbe.notiservice.repository;

import com.harbe.notiservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiRepository extends JpaRepository<Notification, Long> {
}
