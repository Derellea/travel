package cn.itcast.travel.dao.impl;

import cn.itcast.travel.categoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class categoryDaoimpl implements categoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {

        return template.query("select * from tab_category",new BeanPropertyRowMapper<Category>(Category.class));
    }

    @Override
    public Category findOne(int cid) {
        return template.queryForObject("select * from tab_category where cid = ?",
                new BeanPropertyRowMapper<Category>(Category.class),cid);
    }


}
