package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.OrderItem;
import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.OrderService;
import io.zhpooer.estore.service.impl.OrderServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("serial")
public class OrderAddServlet extends HttpServlet {
    OrderService orderService = (OrderService)ServiceFactory.generate(OrderServiceImpl.class);
    
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    // 将请求数据封装到model对象中
    Order order = new Order();
    try {
        BeanUtils.populate(order, req.getParameterMap());
    } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
    }

    // 生成订单号
    String id = UUID.randomUUID().toString();
    order.setId(id);

    order.setPaystate(0);
    order.setCreatetime(new Timestamp(System.currentTimeMillis()));

    User user = (User)req.getSession().getAttribute("user");
    order.setUser_id(user.getId());

    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    // 封转订单项项目
    Map<Product, Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
    for (Map.Entry<Product, Integer> entry: cart.entrySet()) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(id);
        orderItem.setProduct_id(entry.getKey().getId());
        orderItem.setBuynum(entry.getValue());
        orderItems.add(orderItem);
    }
    orderService.addOrder(order, orderItems);

    req.getSession().removeAttribute("cart");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
