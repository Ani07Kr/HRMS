package com.magadhUniversity.repository;

import com.magadhUniversity.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    // Optional: Get active announcements (those that haven't expired)
    List<Announcement> findByExpiryAtIsNullOrExpiryAtAfter(LocalDateTime now);

    // Optional: Get expired announcements
    List<Announcement> findByExpiryAtBefore(LocalDateTime now);
}
