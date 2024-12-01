package com.magadhUniversity.service;

import com.magadhUniversity.model.Announcement;
import com.magadhUniversity.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    // Create Announcement with Expiry Date
    public Announcement createAnnouncement(String title, String content, String postedBy, LocalDateTime expiryAt) {
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setContent(content);
        announcement.setPostedBy(postedBy);
        announcement.setPostedAt(LocalDateTime.now());
        announcement.setExpiryAt(expiryAt);
        return announcementRepository.save(announcement);
    }

    // Get all active announcements
    public List<Announcement> getAllAnnouncements() {
        LocalDateTime now = LocalDateTime.now();
        return announcementRepository.findAll().stream()
                .filter(announcement -> announcement.getExpiryAt() == null || announcement.getExpiryAt().isAfter(now))
                .toList();
    }

    // Get expired announcements
    public List<Announcement> getExpiredAnnouncements() {
        LocalDateTime now = LocalDateTime.now();
        return announcementRepository.findAll().stream()
                .filter(announcement -> announcement.getExpiryAt() != null && announcement.getExpiryAt().isBefore(now))
                .toList();
    }

    // Update Announcement content and expiry date
    public Announcement updateAnnouncement(Long id, String newContent, LocalDateTime newExpiryAt) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Announcement not found"));
        announcement.setContent(newContent); // Update content
        announcement.setExpiryAt(newExpiryAt); // Update expiry date
        return announcementRepository.save(announcement);
    }

    // Delete Announcement by ID
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}
