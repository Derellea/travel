package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.userDaoimpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.userDao;
import cn.itcast.travel.userService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class userServiceimpl implements userService {
    private userDao userdao=new userDaoimpl();
    @Override
    public boolean register(User user) {
        if(userdao.findUser(user)!=null){
            return false;
        }else{
            user.setStatus("N");
            String uuid=UuidUtil.getUuid();
            String text="点击激活<a href="+"http://localhost/travel/user/active?code="+uuid+">黑马旅游网</a>";
            user.setCode(uuid);
            userdao.save(user);
            MailUtils.sendMail(user.getEmail(),text,"黑马旅游网激活邮件" );
        }
        return true;
    }

    @Override
    public boolean active(String code) {
        User user=userdao.findByCode(code);
        if(user!=null){
            userdao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    //uid：登录成功 -1：用户不存在 -2：密码错误 -3：未激活
    public int login(User user) {
        User u=userdao.findUser(user);
        if(u==null){
            return -1;
        }else{
            if(!user.getPassword().equals(u.getPassword())){
                return -2;
            }
            else if(u.getStatus().equals("N")){
                return -3;
            }else{
                return u.getUid();
            }
        }

    }
}
