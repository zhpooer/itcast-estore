package io.zhpooer.estore.service.impl;

import io.zhpooer.estore.Constants;
import io.zhpooer.estore.dao.UserDao;
import io.zhpooer.estore.dao.impl.UserDaoImpl;
import io.zhpooer.estore.entity.User;
import io.zhpooer.estore.service.UserService;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoImpl();

    @Override
    public void regist(User u) {
        // 发送激活邮件
        String activeCode = UUID.randomUUID().toString();

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth","true");
        Session session = Session.getInstance(props);


        // TODO 提取发邮件方法, 申请发邮件账号
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("zhpooer@163.com"));
            msg.setRecipient(RecipientType.TO,
                    new InternetAddress(u.getEmail()));
            msg.setSubject("欢迎您加入, 请激活");
            String url = "http://localhost:8080/active?activecode="
                    + activeCode;
            String content = "<a href='" + url + "'>点击激活</a>";
            msg.setContent(content, "text/html;charset=utf-8");
            Transport trans = session.getTransport();
            trans.connect("zhpooer", "86618563zb");
            trans.sendMessage(msg, msg.getRecipients(RecipientType.TO));
            trans.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        u.setActive(0);
        u.setActivecode(activeCode);
        u.setRole("normal");
        dao.insert(u);
    }

    @Override
    public int active(String code) {
        User user = dao.findByActiveCode(code);
        if (user == null) {
            // 激活码无效
            return Constants.ACTIVEMAIL_INVALIDATE;
        } else {
            // 已经激活
            if (user.getActive() == 1) {
                return Constants.ACTIVEMAIL_HASACTIVE;
            } else {
                // 需要激活
                dao.updateActive(user.getId());
                return Constants.ACTIVEMAIL_OK;
            }
        }
    }

}
