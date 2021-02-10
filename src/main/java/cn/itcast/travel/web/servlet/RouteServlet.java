package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.favoriteService;
import cn.itcast.travel.routeService;
import cn.itcast.travel.service.impl.favoriteServiceimpl;
import cn.itcast.travel.service.impl.routeServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private routeService routeService=new routeServiceimpl();
    private favoriteService favoriteservice=new favoriteServiceimpl();


    /**
    * @Description: 分页查询
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/9
    */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageStr=req.getParameter("currentPage");
        String pageSizeStr=req.getParameter("pageSize");
        String cidStr=req.getParameter("cid"); // 'null'
        String rname=req.getParameter("rname"); // 'null'
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");
        //处理数据
        if("null".equals(rname)){
            rname="%";
        }
        int cid=0;
        if(cidStr!=null && cidStr.length()>0 && !"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        int pageSize=5;
        if(pageSizeStr!=null && pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }
        int currentPage=1;
        if(currentPageStr!=null && currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }

        pageBean<Route> pb = routeService.pageQuery(cid, pageSize, currentPage,rname);

        writeValue(pb,resp);
    }

    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rid = Integer.parseInt(req.getParameter("rid"));
        Route route=routeService.findOne(rid);
        writeValue(route,resp);

    }

    public void findFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rid=Integer.parseInt(req.getParameter("rid"));
        User user = (User) req.getSession().getAttribute("user");
        //如果没有登录uid就=0这样数据库也搜不出来
        int uid=0;
        if(user!=null){
            uid=user.getUid();
        }
        writeValue(favoriteservice.findByRidAndUid(rid,uid), resp);
    }

    public void countFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rid=Integer.parseInt(req.getParameter("rid"));
        writeValue(favoriteservice.countFavorite(rid),resp);
    }

    public void changeFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rid=Integer.parseInt(req.getParameter("rid"));
        User user = (User) req.getSession().getAttribute("user");
        //如果没有登录uid就=0这样数据库也搜不出来
        int uid=0;
        if(user!=null){
            uid=user.getUid();
            favoriteservice.changeFavorite(rid,uid);
            writeValue(true,resp);
        }else{
           writeValue(false,resp);
        }

    }
}
