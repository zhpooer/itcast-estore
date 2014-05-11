package io.zhpooer.estore.service;

import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;
import io.zhpooer.estore.entity.User;

import java.util.List;

public interface OrderService {

    void addOrder(Order order, List<OrderItem> orderItems);

    List<Order> showOrders(User user);

    void cancelOrder(String orderid);

    void cleanUnpayOrders();

}
