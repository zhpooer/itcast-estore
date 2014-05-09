package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Product;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteCartItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        @SuppressWarnings("unchecked")
        Map<Product, Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");

        Product product = new Product();
        product.setId(id);

        // 因为重写的hashCode和equals
        cart.remove(product);
        // 跳转回购物车页面
        resp.sendRedirect("/cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
