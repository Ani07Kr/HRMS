package com.magadhUniversity.service;

import com.magadhUniversity.model.Event;
import com.magadhUniversity.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByDate(LocalDate date) {
        return eventRepository.findByEventDate(date);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }
}
