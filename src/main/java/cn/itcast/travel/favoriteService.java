package cn.itcast.travel;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface favoriteService {
    public boolean findByRidAndUid(int rid,int uid);
    public int countFavorite(int rid);

    public void changeFavorite(int rid, int uid);

    public List<Route> findAll (int uid);

    public List<Route> findWithRank();
}
