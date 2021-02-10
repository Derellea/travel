package cn.itcast.travel;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface routeDao {
    //模糊搜索
    public int findTotalCount(int cid,String rname);
    //根据页查询
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);

    public Route findByRid(int rid);

    //根据收藏数排序查询
    public List<Route> findWithRank();

    //改变收藏后更改count
    public void addCount(int rid);
    public void delCount(int rid);

    //查找单条的收藏数
    public int findCount(int rid);
}
