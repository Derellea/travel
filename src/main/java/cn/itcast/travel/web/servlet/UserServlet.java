package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.userServiceimpl;
import cn.itcast.travel.userService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private userService us=new userServiceimpl();
    /**
    * @Description: 用户激活
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/8
    */
   public void active(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

       String code=req.getParameter("code");
       if(us.active(code)){
           resp.getWriter().write("激活成功,请<a href=/login.html>登录</a>");
       }else{
           resp.getWriter().write("激活失败");
       }
    }
    /**
    * @Description: 用户退出
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/8
    */
    public void exit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
    /**
    * @Description: 用户登录
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/8
    */
    public void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String checkCode=(String)req.getSession().getAttribute("CHECKCODE_SERVER");
        req.getSession().removeAttribute("CHECKCODE_SERVER");
        ResultInfo info=new ResultInfo();
        if(checkCode==null||!checkCode.equalsIgnoreCase(req.getParameter("check"))){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

        }else{
            User user=new User();
            try {
                BeanUtils.populate(user,req.getParameterMap());

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            int login = us.login(user);
            switch (login){
                case -1:{
                    info.setFlag(false);
                    info.setErrorMsg("用户不存在");
                    break;
                }
                case -2:{
                    info.setFlag(false);
                    info.setErrorMsg("用户名或密码错误");
                    break;
                }
                case -3:{
                    info.setFlag(false);
                    info.setErrorMsg("您尚未激活，请激活");
                    break;
                }
                default:{
                    user.setUid(login);
                    req.getSession().setAttribute("user",user);
                    info.setFlag(true);
                    info.setErrorMsg("登录成功");
                    break;
                }


            }


        }
        new ObjectMapper().writeValue(resp.getWriter(),info);

    }

    /**
    * @Description: 用户注册
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/8
    */
    public void regist(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        ResultInfo info=new ResultInfo();
        String checkCode=(String)req.getSession().getAttribute("CHECKCODE_SERVER");
        req.getSession().removeAttribute("CHECKCODE_SERVER");
        if(checkCode==null||!checkCode.equalsIgnoreCase(req.getParameter("check"))){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            writeValue(info,resp);
            return;
        }
        User user=new User();
        try {
            BeanUtils.populate(user,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag=us.register(user);
        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名重复");
        }
        writeValue(info,resp);
    }
    /**
    * @Description: 获取用户名
    * @Param: [req, resp]
    * @return: void
    * @Author: wch
    * @Date: 2021/2/8
    */
    public void findOne(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        User user=(User)req.getSession().getAttribute("user");
        System.out.println(user);
        writeValue(user,resp);
    }
}
