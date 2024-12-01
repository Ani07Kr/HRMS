package com.magadhUniversity.controller;

import com.magadhUniversity.model.Announcement;
import com.magadhUniversity.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    // View all announcements (Students and Admins)
    @GetMapping
    public String viewAnnouncements(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "view_announcements";
    }

    // Create new announcement (Admins and Employees)
    @GetMapping("/create")
    public String createAnnouncementForm() {
        return "create_announcement";
    }

    @PostMapping("/create")
    public String createAnnouncement(@RequestParam String title,
                                     @RequestParam String content,
                                     @RequestParam String postedBy,
                                     @RequestParam String expiryAt) {
        LocalDateTime expiryDateTime = LocalDateTime.parse(expiryAt); // Parse expiry date
        announcementService.createAnnouncement(title, content, postedBy, expiryDateTime);
        return "redirect:/announcements";
    }

    // View expired announcements
    @GetMapping("/expired")
    public String viewExpiredAnnouncements(Model model) {
        List<Announcement> expiredAnnouncements = announcementService.getExpiredAnnouncements();
        model.addAttribute("expiredAnnouncements", expiredAnnouncements);
        return "view_expired_announcements";
    }

    // Update announcement content and expiry date
    @PostMapping("/update/{id}")
    public String updateAnnouncement(@PathVariable Long id,
                                     @RequestParam String newContent,
                                     @RequestParam String newExpiryAt) {
        LocalDateTime newExpiryDateTime = LocalDateTime.parse(newExpiryAt); // Parse new expiry date
        announcementService.updateAnnouncement(id, newContent, newExpiryDateTime);
        return "redirect:/announcements/expired";
    }

    // Delete announcement (Admins only)
    @PostMapping("/delete/{id}")
    public String deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return "redirect:/announcements";
    }
}
