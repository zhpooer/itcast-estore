package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Order;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.OrderService;
import io.zhpooer.estore.service.impl.OrderServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class OrderSearchServlet extends HttpServlet {

        OrderService orderService = (OrderService)ServiceFactory.generate(OrderServiceImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 从Session中获取用户登陆信息
        User user = (User)req.getSession().getAttribute("user");
        List<Order> orders = orderService.showOrders(user);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher(req.getContextPath() + "/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
