package cn.itcast.travel;

import cn.itcast.travel.domain.Favorite;

import java.util.List;

public interface favoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);
    public List<Integer> findAllRidbyUid(int uid);
    public int countFavorite(int rid);
    public void addFavorite(int rid, int uid);
    public void delFavorite(int rid,int uid);
}
