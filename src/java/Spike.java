import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Spike {

    public static void main(String[] args) {
        String activeCode = UUID.randomUUID().toString();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth","true"); 

//        Session session = Session.getInstance(props, new MyAuthenticator("zhpooer", "86618563zb"));
        Session session = Session.getInstance(props);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("zhpooer@163.com"));
            msg.setRecipient(RecipientType.TO,
                    new InternetAddress("zhpooer@gmail.com"));
            msg.setSubject("欢迎您加入, 请激活");
            String url = "http://localhost:8080/active?activecode="
                    + activeCode;
            String content = "<a href='" + url + "'>点击激活</a>";
            msg.setContent(content, "text/html;charset=utf-8");
//            Transport trans = session.getTransport();
            Object o = null;
            String s = (String)o;
            System.out.println(s);
//            Transport.send(msg);
//            trans.connect("zhpooer", "86618563zb");
//            trans.sendMessage(msg, msg.getRecipients(RecipientType.TO));
//            trans.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


class MyAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}   
