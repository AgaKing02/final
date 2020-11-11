package kz.team.se1908.repositories.interfaces.indirect;

import kz.team.se1908.DTOS.ClubStudent;

import java.util.List;

public interface ClubStudentRepository extends EntityRepository<ClubStudent> {
    ClubStudent getClubStudentBySID(long id);

    List<ClubStudent> getClubStudentByCID(long id);
}
