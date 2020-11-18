package repositories.implementations;

import DTOS.EventStudent;
import models.Event;
import models.User;
import repositories.implementations.database.RepositoryImpl;
import repositories.implementations.indirect.EventStudentRepositoryImpl;
import repositories.interfaces.EventRepository;
import repositories.interfaces.Repository;
import repositories.interfaces.UserRepository;
import repositories.interfaces.indirect.EventStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EventRepositoryImpl implements EventRepository {
    private final EventStudentRepository eventStudentRepository = new EventStudentRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final Repository repository = new RepositoryImpl();

    @Override
    public List<Event> getAllEvents() {
        return queryLight("SELECT * FROM events");

    }

    @Override
    public Event getEventByEvent(String event) {
        return queryOne("SELECT * FROM events WHERE event='" + event + "' LIMIT 1");
    }

    @Override
    public Event getEventById(long id) {
        return queryOne("SELECT * FROM events WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public Event getEventByIdForEdit(long id) {
        return queryOneLight("SELECT * FROM events WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public List<User> getStudentsByEvent(Event event) {
        List<User> userList = new ArrayList<>();
        List<EventStudent> eventStudents = eventStudentRepository.getEventStudentByEID(event.getId());
        eventStudents.forEach(e -> userList.add(userRepository.getUserById(e.getStudentid())));
        return userList;
    }

    @Override
    public List<Event> getMyEvents(User user) {
        List<Event> myEvents = new LinkedList<>();
        List<EventStudent> eventStudents = eventStudentRepository.getEventStudentBySID(user.getId());
        eventStudents.forEach(e -> myEvents.add(getEventByIdForEdit(e.getEventid())));
        return myEvents;
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
        String sql = "INSERT INTO events(event,description) values(?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getEvent());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.execute();
            if (entity.getListeners() != null) {
                Event event = getEventByEvent(entity.getEvent());
                entity.getListeners().forEach(e -> eventStudentRepository.add(new EventStudent(event.getId(), e.getId())));
            }
            preparedStatement.close();
            repository.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(preparedStatement).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void update(Event entity) {
        String sql = "UPDATE events SET event=?,description=? where id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getEvent());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
            preparedStatement.close();
            repository.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(preparedStatement).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void remove(Event entity) {
        String sql = "DELETE FROM events where id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            eventStudentRepository.getEventStudentByEID(entity.getId()).forEach(eventStudentRepository::remove);
            preparedStatement.execute();
            preparedStatement.close();
            repository.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(preparedStatement).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public List<Event> query(String sql) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            List<Event> events = new ArrayList<>();
            while (rs.next()) {
                Event event = new Event(
                        rs.getLong("id"),
                        rs.getString("event"),
                        rs.getString("description")
                );
                eventStudentRepository
                        .getEventStudentByEID(event.getId())
                        .forEach(eventStudent -> event.addListener(userRepository
                                .getUserById(eventStudent.getStudentid())));
                events.add(event);

            }
            return events;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(rs).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Objects.requireNonNull(stmt).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                repository.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;

    }

    public List<Event> queryLight(String sql) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            List<Event> events = new ArrayList<>();
            while (rs.next()) {
                Event event = new Event(
                        rs.getLong("id"),
                        rs.getString("event"),
                        rs.getString("description")
                );
                events.add(event);
            }
            return events;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(rs).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Objects.requireNonNull(stmt).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                repository.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Event queryOne(String sql) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Event event = new Event(
                        rs.getLong("id"),
                        rs.getString("event"),
                        rs.getString("description")
                );
                eventStudentRepository
                        .getEventStudentByEID(event.getId())
                        .forEach(eventStudent -> event.addListener(userRepository
                                .getUserById(eventStudent.getStudentid())));
                return event;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(rs).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Objects.requireNonNull(stmt).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                repository.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

    public Event queryOneLight(String sql) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = repository.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Event(
                        rs.getLong("id"),
                        rs.getString("event"),
                        rs.getString("description")
                );
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(rs).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Objects.requireNonNull(stmt).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                repository.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }
}
