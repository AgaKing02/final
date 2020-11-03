package kz.team.se1908.repositories.interfaces;

import kz.team.se1908.DTOS.EventStudent;
import kz.team.se1908.models.Event;
import kz.team.se1908.models.User;

import java.util.List;

public interface EventRepository extends EntityRepository<Event> {

    List<Event> getAllEvents();

    Event getEventByEvent(String event);

    Event getEventById(long id);

    List<User> getStudentsByEvent(Event event);

    void removeUserFromEvent(EventStudent eventStudent);

    void addUserToEvent(EventStudent eventStudent);
}
