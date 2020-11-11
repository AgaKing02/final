package kz.team.se1908.repositories.interfaces.indirect;

import kz.team.se1908.DTOS.GroupStudent;
import kz.team.se1908.repositories.interfaces.EntityRepository;

import java.util.List;

public interface GroupStudentRepository extends EntityRepository<GroupStudent> {
    GroupStudent getGroupStudentBySID(long id);

    List<GroupStudent> getGroupStudentByGID(long id);


}
