package cn.itcast.travel;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

public interface routeService {
    //根据分类和页数查询
    public pageBean<Route> pageQuery(int cid, int pageSize, int currentPage,String rname);

    //根据rid找到单个的路线
    public Route findOne(int rid);
}
