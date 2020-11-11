package kz.team.se1908.controllers.controlpanel;

import kz.team.se1908.services.implementations.NewsServiceImpl;
import kz.team.se1908.services.interfaces.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsLogger")
public class NewsLogger extends HttpServlet {
    private final NewsService newsService=new NewsServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
