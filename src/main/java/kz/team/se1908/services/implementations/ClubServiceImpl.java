package kz.team.se1908.services.implementations;

import kz.team.se1908.DTOS.ClubStudent;
import kz.team.se1908.models.Club;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.implementations.ClubRepositoryImpl;
import kz.team.se1908.repositories.interfaces.ClubRepository;
import kz.team.se1908.services.interfaces.ClubService;

import java.util.List;

public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository = new ClubRepositoryImpl();

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.getAllClubs();
    }

    @Override
    public Club getClubByName(String event) {
        return clubRepository.getClubByName(event);
    }

    @Override
    public Club getClubById(long id) {
        return clubRepository.getClubById(id);
    }

    @Override
    public List<User> getStudentsByClub(Club club) {
        return clubRepository.getStudentsByClub(club);
    }

    @Override
    public void removeUserFromClub(ClubStudent clubStudent) {
        clubRepository.removeUserFromClub(clubStudent);
    }

    @Override
    public void addUserToClub(ClubStudent clubStudent) {
        clubRepository.addUserToClub(clubStudent);
    }

    @Override
    public void add(Club entity) {
        clubRepository.add(entity);
    }

    @Override
    public void update(Club entity) {
        clubRepository.update(entity);
    }

    @Override
    public void remove(Club entity) {
        clubRepository.remove(entity);
    }
}
