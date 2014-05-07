package io.zhpooer.estore.service.impl;

import io.zhpooer.estore.dao.UserDao;
import io.zhpooer.estore.dao.impl.UserDaoImpl;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoImpl();
    
    @Override
    public void regist(User u) {
        // TODO 激活邮件
        u.setRole("normal");
        dao.insert(u);
    }

}
