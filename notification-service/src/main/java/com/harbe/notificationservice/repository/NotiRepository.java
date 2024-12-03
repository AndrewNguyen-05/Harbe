package com.harbe.notificationservice.repository;

import com.harbe.notificationservice.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotiRepository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT * FROM Notifications n WHERE " +
            "n.title LIKE CONCAT('%', :title, '%')", nativeQuery = true
    )
    Page<Notification> searchByTitle(@Param("title") String title, Pageable pageable);
}
