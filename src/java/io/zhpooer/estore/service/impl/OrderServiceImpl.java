package io.zhpooer.estore.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import io.zhpooer.estore.dao.OrderDao;
import io.zhpooer.estore.dao.impl.OrderDaoImpl;
import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    public static Logger LOG = Logger.getLogger(OrderServiceImpl.class);  

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

    @Override
    public void cancelOrder(String orderid) {
        LOG.debug("cancel order" + orderid);
        orderDao.deleteOrderItem(orderid);
        orderDao.deleteOrder(orderid);
    }

    @Override
    public void cleanUnpayOrders() {
        LOG.debug("clean unpay orders");
        // 查询所有订单
        List<Order> orders = orderDao.findAllOrders();
        for (Order order : orders) {
            if (order.getPaystate() == 0
                    && System.currentTimeMillis()
                            - order.getCreatetime().getTime() >= 1000 * 60 * 60) {
                cancelOrder(order.getId());
            }
        }
    }
}
