package cn.itcast.travel.web.servlet;

import cn.itcast.travel.categoryService;
import cn.itcast.travel.service.impl.categoryServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private categoryService cs=new categoryServiceimpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeValue(cs.findAll(),resp);
    }



}
