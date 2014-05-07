package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.UserService;
import io.zhpooer.estore.service.impl.UserServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

    UserService us = (UserService) ServiceFactory
            .generate(UserServiceImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User tmpUser = new User();
        try {
            BeanUtils.populate(tmpUser, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        User loginUser = us.login(tmpUser);
        if (loginUser != null) {
            if (loginUser.getActive() == 0) {
                req.setAttribute("error", "还没有激活");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                if (req.getParameter("remember") != null) {
                    Cookie cookie = new Cookie("email", loginUser.getEmail());
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24); // 一天
                    resp.addCookie(cookie);
                }
                // TODO 可以增加自动登陆功能
                req.getSession().setAttribute("user", loginUser);
                resp.sendRedirect(req.getContextPath() + "/");
            }
        } else {
            req.setAttribute("error", "密码错误或者用户不存在");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
