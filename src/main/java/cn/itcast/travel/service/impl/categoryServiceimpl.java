package cn.itcast.travel.service.impl;

import cn.itcast.travel.categoryDao;
import cn.itcast.travel.categoryService;
import cn.itcast.travel.dao.impl.categoryDaoimpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class categoryServiceimpl implements categoryService {
    private categoryDao dao=new categoryDaoimpl();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        final Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs=null;
        //如果redis中没有
        if(categorys==null ||categorys.size()==0){
            cs = dao.findAll();
            for (Category c : cs) {
                jedis.zadd("category",c.getCid(),c.getCname());
            }
            //如果有的话，转为list
        }else{
            cs=new LinkedList<Category>();
            for(Tuple tuple:categorys){
                Category c=new Category();
                c.setCname(tuple.getElement());
                c.setCid((int)tuple.getScore());
                cs.add(c);
            }
        }
        return cs;
    }
}
