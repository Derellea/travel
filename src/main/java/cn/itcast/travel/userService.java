package cn.itcast.travel;

import cn.itcast.travel.domain.User;

public interface userService {
    public boolean register(User user);
    public boolean active(String code);
    public int login(User user);
}
