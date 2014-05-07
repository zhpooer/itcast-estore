package io.zhpooer.estore.service;

import io.zhpooer.estore.entity.User;

public interface UserService {
    public void regist(User u);

    public int active(String code);
}
