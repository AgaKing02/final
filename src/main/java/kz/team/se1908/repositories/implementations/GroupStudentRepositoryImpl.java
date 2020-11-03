package kz.team.se1908.repositories.implementations;

import kz.team.se1908.DTOS.GroupStudent;
import kz.team.se1908.repositories.interfaces.GroupStudentRepository;

import java.util.List;

public class GroupStudentRepositoryImpl implements GroupStudentRepository {
    @Override
    public GroupStudent getGroupStudentBySID(long id) {
        return queryOne("SELECT * FROM groupstudent where studentid="+id+"LIMIT 1");

    }

    @Override
    public List<GroupStudent> getGroupStudentByGID(long id) {
        return query("SELECT * FROM groupstudent where groupid="+id);
    }

    @Override
    public void add(GroupStudent entity) {

    }

    @Override
    public void update(GroupStudent entity) {

    }

    @Override
    public void remove(GroupStudent entity) {

    }

    @Override
    public List<GroupStudent> query(String sql) {
        return null;
    }

    @Override
    public GroupStudent queryOne(String sql) {
        return null;
    }
}
