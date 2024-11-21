package com.magadhUniversity.controller;



import com.magadhUniversity.model.Announcement;
import com.magadhUniversity.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                     @RequestParam String postedBy) {
        announcementService.createAnnouncement(title, content, postedBy);
        return "redirect:/announcements";
    }

    // Delete announcement (Admins only)
    @PostMapping("/delete/{id}")
    public String deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return "redirect:/announcements";
    }
}
