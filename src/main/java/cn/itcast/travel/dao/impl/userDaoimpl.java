package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.userDao;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



public class userDaoimpl implements userDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUser(User user) {
        User u=null;
        try {
            u= template.queryForObject("select * from tab_user where username=?",
                    new BeanPropertyRowMapper<User>(User.class),user.getUsername());
        }catch (Exception e){

        }
        return u;
    }

    @Override
    public void save(User user) {
        template.update("insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)",
                user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());

    }

    @Override
    public User findByCode(String code) {
        User user=null;
        try {
            user=template.queryForObject("select * from tab_user where code=?",
                    new BeanPropertyRowMapper<User>(User.class),code);
        }catch (Exception e){

        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        template.update("update tab_user set status='Y' where uid=?",user.getUid());
    }

}
