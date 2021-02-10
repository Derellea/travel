package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.favoriteDao;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class favoriteDaoimpl implements favoriteDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        String sql="select * from tab_favorite where rid = ? and uid = ? ";
        Favorite favorite = null ;
        try {
            favorite=template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        }catch (Exception e){

        }
        return favorite;
    }

    @Override
    public List<Integer> findAllRidbyUid(int uid) {
        String sql="select rid from tab_favorite where uid = ? ";
        List<Integer> list=null;
        try {
            list=template.queryForList(sql,Integer.class,uid);
        }catch (Exception e ){

        }
        return list;
    }

    @Override
    public int countFavorite(int rid) {
        String sql="select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql="insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);

    }

    public void delFavorite(int rid,int uid){
        String sql="delete from tab_favorite where rid = ? and uid = ? ";
        template.update(sql,rid,uid);
    }
}
