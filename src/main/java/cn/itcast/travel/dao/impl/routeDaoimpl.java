package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.routeDao;
import cn.itcast.travel.util.JDBCUtils;
import com.mysql.cj.result.Row;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class routeDaoimpl implements routeDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
        String sql="select count(*) from tab_route where 1 = 1 ";
        List list=new ArrayList();
        if(cid!=0){
            sql+="and cid = ? ";
            list.add(cid);
        }
        if(rname!=null && rname.length()>0){
            sql+=" and rname like ? ";
            list.add("%"+rname+"%");
        }

        return template.queryForObject(sql,Integer.class,list.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        String sql="select * from tab_route where 1 = 1 ";
        List list=new ArrayList();
        if(cid!=0){
            sql+=" and cid = ? ";
            list.add(cid);
        }
        if(rname!=null && rname.length()>0){
            sql+=" and rname like ? ";
            list.add("%"+rname+"%");
        }
        sql+=" limit ? , ? ";
        list.add(start);
        list.add(pageSize);


        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
    }

    @Override
    public Route findByRid(int rid) {
        String sql="select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public List<Route> findWithRank() {
        String sql="select * from tab_route ORDER BY COUNT DESC";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Override
    public void addCount(int rid) {
        String sql ="update tab_route set count = ? where rid = ? ";
        int count=findCount(rid)+1;
        template.update(sql,count,rid);
    }

    @Override
    public void delCount(int rid) {
        String sql ="update tab_route set count = ? where rid = ? ";
        int count=findCount(rid)-1;
        template.update(sql,count,rid);
    }

    @Override
    public int findCount(int rid) {
        String sql ="select count from tab_route where rid = ? ";
        return template.queryForObject(sql,Integer.class,rid);
    }
}
