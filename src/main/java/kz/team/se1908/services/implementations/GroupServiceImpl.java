package kz.team.se1908.services.implementations;

import kz.team.se1908.DTOS.GroupStudent;
import kz.team.se1908.models.Group;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.implementations.GroupRepositoryImpl;
import kz.team.se1908.repositories.interfaces.GroupRepository;
import kz.team.se1908.services.interfaces.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository=new GroupRepositoryImpl();

    @Override
    public List<Group> getAllGroups() {
       return groupRepository.getAllGroups();
    }

    @Override
    public List<Group> getGroupsByYear(int year) {
       return groupRepository.getGroupsByYear(year);
    }

    @Override
    public Group getGroupById(long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public Group getGroupByName(String name) {
        return groupRepository.getGroupByName(name);
    }

    @Override
    public List<User> getStudentsByGroup(Group group) {
        return groupRepository.getStudentsByGroup(group);
    }

    @Override
    public Group getGroupByStudent(User student) {
        return groupRepository.getGroupByStudent(student);
    }

    @Override
    public void removeUserFromGroup(GroupStudent groupStudent) {
        groupRepository.removeUserFromGroup(groupStudent);
    }

    @Override
    public void addUserToGroup(GroupStudent groupStudent) {
       groupRepository.addUserToGroup(groupStudent);
    }

    @Override
    public void add(Group entity) {
    groupRepository.add(entity);
    }

    @Override
    public void update(Group entity) {
        groupRepository.update(entity);
    }

    @Override
    public void remove(Group entity) {
        groupRepository.remove(entity);
    }

}
