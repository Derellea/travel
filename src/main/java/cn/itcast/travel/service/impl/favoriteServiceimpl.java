package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.favoriteDaoimpl;
import cn.itcast.travel.dao.impl.routeDaoimpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.favoriteDao;
import cn.itcast.travel.favoriteService;
import cn.itcast.travel.routeDao;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class favoriteServiceimpl implements favoriteService {
    private favoriteDao favoritedao=new favoriteDaoimpl();
    private routeDao routedao=new routeDaoimpl();
    @Override
    public boolean findByRidAndUid(int rid, int uid) {
        return favoritedao.findByRidAndUid(rid,uid)!=null;
    }

    @Override
    public int countFavorite(int rid) {
        return favoritedao.countFavorite(rid);
    }

    @Override
    public void changeFavorite(int rid, int uid) {
        if(favoritedao.findByRidAndUid(rid,uid)==null){
            //favorite表中增加，route表中count列也需要增加
            favoritedao.addFavorite(rid,uid);
            routedao.addCount(rid);
        }else{
            favoritedao.delFavorite(rid,uid);
            routedao.delCount(rid);
        }
    }

    @Override
    public List<Route> findAll(int uid) {
        List<Integer> ridList=favoritedao.findAllRidbyUid(uid);
        List<Route> routeList=new LinkedList<Route>();
        if(ridList==null){
            return null;
        }else{
            for (Integer rid : ridList) {
                Route route = routedao.findByRid(rid);
                routeList.add(route);
            }
            return routeList;
        }

    }

    @Override
    public List<Route> findWithRank() {
        List<Route> routes = routedao.findWithRank();
        return routes;
    }
}
