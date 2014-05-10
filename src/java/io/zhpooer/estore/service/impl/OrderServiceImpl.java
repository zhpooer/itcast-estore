package io.zhpooer.estore.service.impl;

import java.util.List;

import io.zhpooer.estore.dao.OrderDao;
import io.zhpooer.estore.dao.impl.OrderDaoImpl;
import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void addOrder(Order order, List<OrderItem> orderItems) {
        orderDao.insert(order, orderItems);
    }

    @Override
    public List<Order> showOrders(User user) {
        if (user.getRole().equals("normal")) {
            // 普通用户
            return orderDao.findOrdersByUser(user.getId());
        } else if (user.getRole().equals("admin")) {
            // 管理员
            return orderDao.findAllOrders();
        } else {
            return null;
        }
    }

}
