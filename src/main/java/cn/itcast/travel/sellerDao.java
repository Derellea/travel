package cn.itcast.travel;

import cn.itcast.travel.domain.Seller;

public interface sellerDao {
    public Seller findBySid(int sid);
}
