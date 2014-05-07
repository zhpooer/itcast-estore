package io.zhpooer.estore.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import io.zhpooer.estore.dao.UserDao;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.utils.TransactionManager;

public class UserDaoImpl implements UserDao {
    QueryRunner qr = new QueryRunner();

    @Override
    public void insert(User u) {
        String sql = "insert into user(email, password, nickname, role, active, activecode) values(?,?,?,?,?,?)";
        try {
            // TODO 用户密码用密文保存
            qr.update(TransactionManager.getConnection(), sql, u.getEmail(),
                    u.getPassword(), u.getNickname(), u.getRole(),
                    u.getActive(), u.getActivecode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByActiveCode(String activeCode) {
        String sql = "select * from user where activecode=?";
        try {
            return qr.query(TransactionManager.getConnection(), sql,
                    new BeanHandler<>(User.class), activeCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateActive(int id) {
        String sql = "update user set active = 1 where id=?";
        try {
            qr.update(TransactionManager.getConnection(), sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(User user) {
        String sql = "select * from user where email=? and password=?";
        try {
            return qr.query(TransactionManager.getConnection(), sql,
                    new BeanHandler<>(User.class), user.getEmail(), user.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
