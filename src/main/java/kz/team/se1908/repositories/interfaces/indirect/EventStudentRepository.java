package kz.team.se1908.repositories.interfaces.indirect;

import kz.team.se1908.DTOS.EventStudent;

import java.util.List;


public interface EventStudentRepository extends EntityRepository<EventStudent> {

    EventStudent getEventStudentBySID(long id);

    List<EventStudent> getEventStudentByEID(long id);

 }
