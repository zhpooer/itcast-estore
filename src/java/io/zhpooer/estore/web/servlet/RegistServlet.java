package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.bean.UserBean;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.UserService;
import io.zhpooer.estore.service.impl.UserServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("serial")
public class RegistServlet extends HttpServlet {
    
    UserService us = (UserService)ServiceFactory.generate(UserServiceImpl.class);
    
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserBean userBean = new UserBean();
        try {
            BeanUtils.populate(userBean, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HttpSession session = req.getSession();
        boolean validate = userBean.validate((String)session.getAttribute("checkcode"));
        session.removeAttribute("checkcode");
        if(!validate) {
            req.setAttribute("error", userBean.getError());
            req.setAttribute("userBean", userBean);
            req.getRequestDispatcher(req.getContextPath() + "/regist.jsp").forward(req, resp);
        }
        User user = new User();
        try {
            BeanUtils.copyProperties(user, userBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO 要先检查用户的邮件是不是已经注册了
        us.regist(user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
