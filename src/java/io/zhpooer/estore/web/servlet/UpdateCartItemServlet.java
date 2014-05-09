package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.service.ProductService;
import io.zhpooer.estore.service.impl.ProductServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateCartItemServlet extends HttpServlet {
    ProductService productService = (ProductService) ServiceFactory
            .generate(ProductServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int num = Integer.parseInt(req.getParameter("num"));

        // 从session获取购物车对象
        @SuppressWarnings("unchecked")
        Map<Product, Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");

        // 服务器校验商品数量(根据id查询商品数量)
        int pnum = productService.findProductNumById(id);
        if (num > pnum) {
            throw new RuntimeException("库存不足");
        }

        // 修改数量
        Product product = new Product();
        product.setId(id);
        if (num == 0) {
            cart.remove(product);
        } else {
            cart.put(product, num); // key不会覆盖, value覆盖
        }
        resp.sendRedirect("/cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
