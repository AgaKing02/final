package repositories.implementations.indirect;

import DTOS.EventStudent;
import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.Repository;
import repositories.interfaces.indirect.EventStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventStudentRepositoryImpl implements EventStudentRepository {
    private final Repository repository = new RepositoryImpl();

    @Override
    public List<EventStudent> getEventStudentBySID(long id) {
        return query("SELECT * FROM eventstudent where studentid=" + id);
    }

    @Override
    public List<EventStudent> getEventStudentByEID(long id) {
        return query("SELECT * FROM eventstudent where eventid=" + id);
    }

    @Override
    public void add(EventStudent entity) {
        String sql = "INSERT INTO eventstudent(eventid,studentid) values(?,?)";
        PreparedStatement preparedStatement=null;

        try {
             preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getEventid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
            preparedStatement.close();
            repository.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                Objects.requireNonNull(preparedStatement).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                repository.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void update(EventStudent entity) {

    }

    @Override
    public void remove(EventStudent entity) {
        String sql = "DELETE FROM eventstudent where eventid=? and studentid=?";
        PreparedStatement preparedStatement=null;
        try {
             preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getEventid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
            preparedStatement.close();
            repository.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                Objects.requireNonNull(preparedStatement).close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                repository.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<EventStudent> query(String sql) {
        Statement stmt = null;
        ResultSet rs=null;
        List<EventStudent> eventStudents = new ArrayList<>();
        try {
            stmt = repository.getConnection().createStatement();
             rs = stmt.executeQuery(sql);
            while (rs.next()) {
                EventStudent eventStudent = new EventStudent(
                        rs.getLong("eventid"),
                        rs.getLong("studentid")
                );
                eventStudents.add(eventStudent);
            }
            stmt.close();
            repository.getConnection().close();
            return eventStudents;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
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
    public EventStudent queryOne(String sql) {
        Statement stmt = null;
        ResultSet rs=null;
        try {
            stmt = repository.getConnection().createStatement();
             rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new EventStudent(
                        rs.getLong("eventid"),
                        rs.getLong("studentid")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
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
