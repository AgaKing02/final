package kz.team.se1908.repositories.implementations;

import kz.team.se1908.models.News;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.interfaces.NewsRepository;
import kz.team.se1908.repositories.interfaces.UserRepository;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
    private final UserRepository userRepository=new UserRepositoryImpl();
    @Override
    public List<News> getAllNews() {
        return query("SELECT * FROM news");

    }

    @Override
    public News getNewsByUser(User user) {
        return queryOne("SELECT * FROM news where publisher="+user.getId()+"LIMIT 1");
    }

    @Override
    public News getNewsById(int id) {
        return queryOne("SELECT * FROM events WHERE id="+id+"LIMIT 1");
    }

    @Override
    public User getNewsAuthor(News news) {
        return userRepository.getUserById(news.publisher.getId());
    }

    @Override
    public void removeNewsByUser(User user) {
        this.remove(getNewsByUser(user));
    }

    @Override
    public void add(News entity) {

    }

    @Override
    public void update(News entity) {

    }

    @Override
    public void remove(News entity) {

    }

    @Override
    public List<News> query(String sql) {
        return null;
    }

    @Override
    public News queryOne(String sql) {
        return null;
    }
}
