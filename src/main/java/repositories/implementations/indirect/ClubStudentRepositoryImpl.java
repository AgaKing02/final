package repositories.implementations.indirect;

import DTOS.ClubStudent;
import repositories.implementations.database.RepositoryImpl;
import repositories.interfaces.Repository;
import repositories.interfaces.indirect.ClubStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClubStudentRepositoryImpl implements ClubStudentRepository {
    private final Repository repository = new RepositoryImpl();

    @Override
    public ClubStudent getClubStudentBySID(long id) {
        return queryOne("SELECT * FROM clubstudent where studentid=" + id + "LIMIT 1");
    }

    @Override
    public List<ClubStudent> getClubStudentByCID(long id) {
        return query("SELECT * FROM clubstudent where clubid=" + id);
    }

    @Override
    public void add(ClubStudent entity) {
        String sql = "INSERT INTO clubstudent(clubid,studentid) values(?,?)";
        PreparedStatement preparedStatement=null;

        try {
             preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getClubid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();

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
    public void update(ClubStudent entity) {

    }

    @Override
    public void remove(ClubStudent entity) {
        String sql = "DELETE FROM clubstudent where clubid=? and studentid=?";
        PreparedStatement preparedStatement=null;
        try {
             preparedStatement = repository.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, entity.getClubid());
            preparedStatement.setLong(2, entity.getStudentid());
            preparedStatement.execute();
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
    public List<ClubStudent> query(String sql) {
        Statement stmt = null;
        ResultSet rs=null;
        List<ClubStudent> clubStudents = new ArrayList<>();
        try {
            stmt = repository.getConnection().createStatement();
             rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ClubStudent clubStudent = new ClubStudent(
                        rs.getLong("clubid"),
                        rs.getLong("studentid")
                );
                clubStudents.add(clubStudent);
            }
            stmt.close();
            repository.getConnection().close();
            return clubStudents;
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
    public ClubStudent queryOne(String sql) {
        Statement stmt = null;
        ResultSet rs=null;
        try {
            stmt = repository.getConnection().createStatement();
             rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new ClubStudent(
                        rs.getLong("clubid"),
                        rs.getLong("studentid")
                );
            }
            stmt.close();
            repository.getConnection().close();
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
