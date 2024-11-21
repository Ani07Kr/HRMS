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

    public Announcement createAnnouncement(String title, String content, String postedBy) {
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setContent(content);
        announcement.setPostedBy(postedBy);
        announcement.setPostedAt(LocalDateTime.now());
        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}
