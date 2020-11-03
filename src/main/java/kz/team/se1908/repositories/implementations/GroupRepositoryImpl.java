package kz.team.se1908.repositories.implementations;

import kz.team.se1908.DTOS.GroupStudent;
import kz.team.se1908.models.Group;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.interfaces.GroupRepository;
import kz.team.se1908.repositories.interfaces.GroupStudentRepository;
import kz.team.se1908.repositories.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryImpl implements GroupRepository {
    private final GroupStudentRepository groupStudentRepository = new GroupStudentRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public List<Group> getAllGroups() {
        return query("SELECT * FROM groups");
    }

    @Override
    public List<Group> getGroupsByYear(int year) {
        return query("SELECT * FROM groups WHERE year=" + year);
    }

    @Override
    public Group getGroupById(long id) {
        return queryOne("SELECT * FROM groups WHERE id=" + id + "LIMIT 1");
    }

    @Override
    public Group getGroupByName(String name) {
        return queryOne("SELECT * FROM groups WHERE name='" + name + "' LIMIT 1");
    }

    @Override
    public List<User> getStudentsByGroup(Group group) {
        List<User> userList = new ArrayList<>();
        List<GroupStudent> groupStudents = groupStudentRepository.getGroupStudentByGID(group.getId());
        groupStudents.forEach(e -> userList.add(userRepository.getUserById(e.getStudentid())));
        return userList;
    }

    @Override
    public Group getGroupByStudent(User student) {
        GroupStudent groupStudent = groupStudentRepository.getGroupStudentBySID(student.getId());
        return getGroupById(groupStudent.getGroupid());
    }


    @Override
    public void removeUserFromGroup(GroupStudent groupStudent) {
     groupStudentRepository.remove(groupStudent);
    }

    @Override
    public void addUserToGroup(GroupStudent groupStudent) {
     groupStudentRepository.add(groupStudent);
    }

    @Override
    public void add(Group entity) {

    }

    @Override
    public void update(Group entity) {

    }

    @Override
    public void remove(Group entity) {

    }

    @Override
    public List<Group> query(String sql) {
        return null;
    }

    @Override
    public Group queryOne(String sql) {
        return null;
    }
}
