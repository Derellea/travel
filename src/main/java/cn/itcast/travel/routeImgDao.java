package cn.itcast.travel;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface routeImgDao {
    public List<RouteImg> findByRid(int rid);
}
