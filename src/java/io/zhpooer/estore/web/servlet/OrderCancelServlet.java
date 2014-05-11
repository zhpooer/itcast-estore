package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.service.OrderService;
import io.zhpooer.estore.service.impl.OrderServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class OrderCancelServlet extends HttpServlet {

    OrderService orderService = (OrderService) ServiceFactory
            .generate(OrderServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderid = req.getParameter("id");
        orderService.cancelOrder(orderid);
        resp.sendRedirect(req.getContextPath() + "/servlet/orderSearch");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
