package kz.team.se1908.repositories.interfaces;

import kz.team.se1908.DTOS.GroupStudent;
import kz.team.se1908.models.Group;
import kz.team.se1908.models.User;

import java.util.List;

public interface GroupRepository extends EntityRepository<Group>{

    List<Group> getAllGroups();

    List<Group> getGroupsByYear(int year);

    Group getGroupById(long id);

    Group getGroupByName(String name);

    List<User> getStudentsByGroup(Group group);

    Group getGroupByStudent(User student);

    void removeUserFromGroup(GroupStudent groupStudent);

    void addUserToGroup(GroupStudent groupStudent);

}
