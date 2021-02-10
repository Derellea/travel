package cn.itcast.travel;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface categoryDao {
    public List<Category> findAll();
    public Category findOne(int cid);
}
