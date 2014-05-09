package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.service.ProductService;
import io.zhpooer.estore.service.impl.ProductServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ProductSearchServlet extends HttpServlet {
    
    ProductService productService = (ProductService)ServiceFactory.generate(ProductServiceImpl.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO 页面优化
        List<Product> products = productService.listAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher(req.getContextPath() + "/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
