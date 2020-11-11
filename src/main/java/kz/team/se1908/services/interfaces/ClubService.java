package kz.team.se1908.services.interfaces;

import kz.team.se1908.DTOS.ClubStudent;
import kz.team.se1908.models.Club;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.interfaces.indirect.EntityService;

import java.util.List;

public interface ClubService extends EntityService<Club> {
    List<Club> getAllClubs();

    Club getClubByName(String event);

    Club getClubById(long id);

    List<User> getStudentsByClub(Club club);

    void removeUserFromClub(ClubStudent clubStudent);

    void addUserToClub(ClubStudent clubStudent);
}
