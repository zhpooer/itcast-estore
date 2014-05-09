package io.zhpooer.estore.service;

import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;

import java.util.List;

public interface OrderService {

    void addOrder(Order order, List<OrderItem> orderItems);

}
