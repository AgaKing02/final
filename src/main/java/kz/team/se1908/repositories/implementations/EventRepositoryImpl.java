package kz.team.se1908.repositories.implementations;

import kz.team.se1908.DTOS.EventStudent;
import kz.team.se1908.models.Event;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.interfaces.EventRepository;
import kz.team.se1908.repositories.interfaces.EventStudentRepository;
import kz.team.se1908.repositories.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    private final EventStudentRepository eventStudentRepository=new EventStudentRepositoryImpl();
    private final UserRepository userRepository=new UserRepositoryImpl();
    @Override
    public List<Event> getAllEvents() {
        return query("SELECT * FROM events");

    }

    @Override
    public Event getEventByEvent(String event) {
        return queryOne("SELECT * FROM events WHERE event='"+event+"' LIMIT 1");
    }

    @Override
    public Event getEventById(long id) {
        return queryOne("SELECT * FROM events WHERE id="+id+"LIMIT 1");
    }

    @Override
    public List<User> getStudentsByEvent(Event event) {
        List<User> userList=new ArrayList<>();
        List<EventStudent> eventStudents=eventStudentRepository.getEventStudentByEID(event.getId());
        eventStudents.forEach(e->userList.add(userRepository.getUserById(e.getStudentid())));
        return userList;
    }



    @Override
    public void removeUserFromEvent(EventStudent eventStudent) {
      eventStudentRepository.remove(eventStudent);
    }

    @Override
    public void addUserToEvent(EventStudent eventStudent) {
     eventStudentRepository.add(eventStudent);
    }

    @Override
    public void add(Event entity) {

    }

    @Override
    public void update(Event entity) {

    }

    @Override
    public void remove(Event entity) {

    }

    @Override
    public List<Event> query(String sql) {
        return null;
    }

    @Override
    public Event queryOne(String sql) {
        return null;
    }
}
