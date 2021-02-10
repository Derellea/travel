package cn.itcast.travel.service.impl;

import cn.itcast.travel.*;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

import java.util.List;

public class routeServiceimpl implements routeService {
    private routeDao routedao= new routeDaoimpl();
    private routeImgDao routeImgdao=new routeImgDaoimpl();
    private sellerDao sellerdao=new sellerDaoimpl();
    private categoryDao categorydao=new categoryDaoimpl();
    private favoriteDao favoritedao=new favoriteDaoimpl();

    @Override
    public pageBean<Route> pageQuery(int cid, int pageSize, int currentPage,String rname) {
        pageBean<Route> pb=new pageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //查询总记录数
        int totalCount=routedao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //计算总页数
        int totalPage=(int) Math.ceil((1.0*totalCount)/pageSize);
        pb.setTotalPage(totalPage);
        //查询list
        int start=(currentPage-1) *pageSize;
        List<Route> list = routedao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);
        return pb;
    }

    @Override
    public Route findOne(int rid) {
        Route route = routedao.findByRid(rid);
        route.setRouteImgList(routeImgdao.findByRid(rid));
        route.setSeller(sellerdao.findBySid(route.getSid()));
        route.setCategory(categorydao.findOne(route.getCid()));
        return route;
    }
}
