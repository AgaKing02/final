package kz.team.se1908.services.implementations;

import kz.team.se1908.models.News;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.implementations.NewsRepositoryImpl;
import kz.team.se1908.repositories.interfaces.NewsRepository;
import kz.team.se1908.services.interfaces.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository=new NewsRepositoryImpl();
    @Override
    public List<News> getAllNews() {
        return newsRepository.getAllNews();
    }

    @Override
    public List<News> getNewsByUser(User user) {
        return newsRepository.getNewsByUser(user);
    }

    @Override
    public News getNewsById(int id) {
        return newsRepository.getNewsById(id);
    }

    @Override
    public User getNewsAuthor(News news) {
        return newsRepository.getNewsAuthor(news);
    }

    @Override
    public void removeNewsByUser(User user) {
        newsRepository.removeNewsByUser(user);
    }

    @Override
    public void add(News entity) {
        newsRepository.add(entity);
    }

    @Override
    public void update(News entity) {
        newsRepository.update(entity);
    }

    @Override
    public void remove(News entity) {
        newsRepository.remove(entity);
    }
}
