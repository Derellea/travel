package cn.itcast.travel;

import cn.itcast.travel.domain.User;

public interface userDao {
    public User findUser(User user);
    public void save(User user);
    public User findByCode(String code);
    public void updateStatus(User user);
}
