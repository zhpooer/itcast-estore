package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.Constants;
import io.zhpooer.estore.service.UserService;
import io.zhpooer.estore.service.impl.UserServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ActiveServlet extends HttpServlet {
    UserService us = (UserService) ServiceFactory
            .generate(UserServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String code = req.getParameter("activecode");
        int result = us.active(code);
        PrintWriter out = resp.getWriter();
        if (result == Constants.ACTIVEMAIL_HASACTIVE) {
            out.print("账户已经激活");
        } else if (result == Constants.ACTIVEMAIL_INVALIDATE) {
            out.println("激活码无效");
        } else if (result == Constants.ACTIVEMAIL_OK) {
            out.println("激活成功");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
