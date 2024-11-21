package com.magadhUniversity.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/events")
    public String eventsPage() {
        return "events"; // Corresponds to the 'events.html' file in the templates folder
    }
}
