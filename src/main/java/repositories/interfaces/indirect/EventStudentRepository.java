package repositories.interfaces.indirect;

import DTOS.EventStudent;

import java.util.List;


public interface EventStudentRepository extends EntityRepository<EventStudent> {

    List<EventStudent> getEventStudentBySID(long id);

    List<EventStudent> getEventStudentByEID(long id);

 }
