package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.favoriteService;
import cn.itcast.travel.service.impl.favoriteServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/favorite/*")
public class favoriteServlet extends BaseServlet{
    private favoriteService favoriteservice=new favoriteServiceimpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) (req.getSession().getAttribute("user"));
        List<Route> routes = favoriteservice.findAll(user.getUid());
        writeValue(routes,resp);
    }

    public void findWithRank(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Route> routes = favoriteservice.findWithRank();
        writeValue(routes,resp);
    }
}
