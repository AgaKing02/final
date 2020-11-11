package kz.team.se1908.services.interfaces;

import kz.team.se1908.models.News;
import kz.team.se1908.models.User;
import kz.team.se1908.repositories.interfaces.indirect.EntityService;

import java.util.List;

public interface NewsService extends EntityService<News> {
    List<News> getAllNews();

    List<News> getNewsByUser(User user);

    News getNewsById(int id);

    User getNewsAuthor(News news);

    void removeNewsByUser(User user);
}
