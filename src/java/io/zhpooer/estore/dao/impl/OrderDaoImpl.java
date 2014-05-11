package io.zhpooer.estore.dao.impl;

import io.zhpooer.estore.dao.OrderDao;
import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;
import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.utils.TransactionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class OrderDaoImpl implements OrderDao {
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public void insert(Order order, List<OrderItem> orderItems) {
        try {
            String orderSql = "insert into orders(id, totalprice, receiverinfo, paystate, createtime, user_id) values(?,?,?,?,?,?)";
            queryRunner.update(TransactionManager.getConnection(), orderSql,
                    order.getId(), order.getTotalprice(),
                    order.getReceiverinfo(), order.getPaystate(),
                    order.getCreatetime(), order.getUser_id());
            // 保存订单项数据
            String orderitemSql = "insert into orderitem(order_id, product_id, buynum) values(?,?,?)";
            List<Object[]> argList = new ArrayList<>();
            for (OrderItem orderItem : orderItems) {
                // 用 batch, 先将参数保存到二维数组
                Object[] orderitemArgs = { orderItem.getOrder_id(),
                        orderItem.getProduct_id(), orderItem.getBuynum() };

                argList.add(orderitemArgs);
            }
            // 将多种参数, 一次提交给服务器, 提高效率
            queryRunner.batch(TransactionManager.getConnection(), orderitemSql,
                    argList.toArray(new Object[0][]));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findOrdersByUser(int id) {
        try {
            String sql = "select * from orders where user_id = ?";
            List<Order> orders = queryRunner.query(
                    TransactionManager.getConnection(), sql,
                    new BeanListHandler<>(Order.class), id);

            // 根据用户id, 查询用户信息
            sql = "select * from user where id=?";
            for (Order order : orders) {
                String nickname = queryRunner.query(
                        TransactionManager.getConnection(), sql,
                        new ScalarHandler<String>("nickname"),
                        order.getUser_id());
                order.setNickname(nickname);
                // 订单关联订单项, 查询订单项
                String sql2 = "select * from orderitem where order_id=?";
                List<OrderItem> orderItems = queryRunner.query(
                        TransactionManager.getConnection(), sql2,
                        new BeanListHandler<>(OrderItem.class), order.getId());
                order.setOrderItems(orderItems);
                // 查询每个订单项 关联商品信息
                for (OrderItem orderItem : orderItems) {
                    String sql4 = "select * from product where id=?";
                    Product product = queryRunner.query(
                            TransactionManager.getConnection(), sql4,
                            new BeanHandler<>(Product.class),
                            orderItem.getProduct_id());
                    orderItem.setProduct(product);
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Order> findAllOrders() {
        try {
            String sql = "select * from orders";
            List<Order> orders = queryRunner.query(
                    TransactionManager.getConnection(), sql,
                    new BeanListHandler<>(Order.class));

            // 根据用户id, 查询用户信息
            sql = "select * from user where id=?";
            for (Order order : orders) {
                String nickname = queryRunner.query(
                        TransactionManager.getConnection(), sql,
                        new ScalarHandler<String>("nickname"),
                        order.getUser_id());
                order.setNickname(nickname);
                // 订单关联订单项, 查询订单项
                String sql2 = "select * from orderitem where order_id=?";
                List<OrderItem> orderItems = queryRunner.query(
                        TransactionManager.getConnection(), sql2,
                        new BeanListHandler<>(OrderItem.class), order.getId());
                order.setOrderItems(orderItems);
                // 查询每个订单项 关联商品信息
                for (OrderItem orderItem : orderItems) {
                    String sql4 = "select * from product where id=?";
                    Product product = queryRunner.query(
                            TransactionManager.getConnection(), sql4,
                            new BeanHandler<>(Product.class),
                            orderItem.getProduct_id());
                    orderItem.setProduct(product);
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderItem(String orderid) {
        String sql = "delete from orderitem where order_id=?";
        try {
            queryRunner
                    .update(TransactionManager.getConnection(), sql, orderid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(String orderid) {
        String sql = "delete from orders where id=?";
        try {
            queryRunner
                    .update(TransactionManager.getConnection(), sql, orderid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
