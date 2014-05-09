package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.service.ProductService;
import io.zhpooer.estore.service.impl.ProductServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddCartServlet extends HttpServlet {
    ProductService productService = (ProductService)ServiceFactory.generate(ProductServiceImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        Map<Product, Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
        if(cart==null) {
            cart = new HashMap<>();
            req.getSession().setAttribute("cart", cart);
        }
        int id = Integer.parseInt(req.getParameter("id"));
        Product p = new Product();
        p.setId(id);
        if(cart.containsKey(p)){
            cart.put(p, cart.get(p)+1);
        } else {
            p = productService.findProduct(id);
            cart.put(p, 1);
        }
        
        resp.getWriter().println("添加完成");
        resp.addHeader("Refresh", "2;url=/servlet/productSearch");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
