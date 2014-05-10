package io.zhpooer.estore.dao;

import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;

import java.util.List;

public interface OrderDao {

    void insert(Order order, List<OrderItem> orderItems);

    List<Order> findOrdersByUser(int id);

    List<Order> findAllOrders();

}
