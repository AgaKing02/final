package kz.team.se1908.repositories.interfaces;

import kz.team.se1908.models.News;
import kz.team.se1908.models.User;

import java.util.List;

public interface NewsRepository extends EntityRepository<News>{

    List<News> getAllNews();

    News getNewsByUser(User user);

    News getNewsById(int id);

    User getNewsAuthor(News news);

    void removeNewsByUser(User user);

}
