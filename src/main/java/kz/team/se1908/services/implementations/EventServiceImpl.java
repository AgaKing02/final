package kz.team.se1908.services.implementations;

import kz.team.se1908.DTOS.EventStudent;
import kz.team.se1908.models.Event;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.implementations.EventRepositoryImpl;
import kz.team.se1908.repositories.interfaces.EventRepository;
import kz.team.se1908.services.interfaces.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository=new EventRepositoryImpl();
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public Event getEventByEvent(String event) {
        return eventRepository.getEventByEvent(event);
    }

    @Override
    public Event getEventById(long id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public List<User> getStudentsByEvent(Event event) {
        return eventRepository.getStudentsByEvent(event);
    }

    @Override
    public void removeUserFromEvent(EventStudent eventStudent) {
      eventRepository.removeUserFromEvent(eventStudent);
    }

    @Override
    public void addUserToEvent(EventStudent eventStudent) {
    eventRepository.addUserToEvent(eventStudent);
    }

    @Override
    public void add(Event entity) {
     eventRepository.add(entity);
    }

    @Override
    public void update(Event entity) {
      eventRepository.update(entity);
    }

    @Override
    public void remove(Event entity) {
      eventRepository.remove(entity);
    }

}
