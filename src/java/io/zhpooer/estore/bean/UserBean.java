package io.zhpooer.estore.bean;

import java.util.HashMap;
import java.util.Map;

public class UserBean {
    private String email;
    private String nickname;
    private String password;
    private String repassword;
    private String checkcode;
    private Map<String, String> error = new HashMap<>();
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean validate(String checkcode) {
        if (checkcode == null || !checkcode.equals(this.checkcode)) {
            error.put("checkcode", "验证码错误");
        } else {
            if (!email.matches(EMAIL_PATTERN)) {
                error.put("email", "请输入正确的email地址");
            }
            if(nickname=="") {
                error.put("nickname", "不要输入空昵称");
            }
            if (password.length() < 3) {
                error.put("password", "密码的长度必须大于3");
            } else if (!password.equals(repassword)) {
                error.put("repassword", "两次密码不一致");
            }
        }
        return error.size()==0;
    }

    public Map<String, String> getError() {
        return error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
