package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    @Override
    /**
    * @Description: 方法派发
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/8
    */
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Uri=req.getRequestURI();
        String MethodName=Uri.substring(Uri.lastIndexOf('/')+1);
        try {
            Method method = this.getClass().getMethod(MethodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp );
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public void writeValue(Object obj,HttpServletResponse resp) throws IOException {
        new ObjectMapper().writeValue(resp.getWriter(),obj);
    }
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
