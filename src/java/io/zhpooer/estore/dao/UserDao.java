package io.zhpooer.estore.dao;

import io.zhpooer.estore.entity.User;

public interface UserDao {
    public void insert(User u);

    public User findByActiveCode(String activeCode);

    public void updateActive(int id);

    public User login(User user);
}
