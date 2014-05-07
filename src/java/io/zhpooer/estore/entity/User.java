package io.zhpooer.estore.entity;

public class User {
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password
                + ", nickname=" + nickname + ", role=" + role + ", active="
                + active + ", activecode=" + activecode + "]";
    }

    private int id;
    private String email;
    private String password;
    private String nickname;
    private String role;
    private int active;
    private String activecode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getActivecode() {
        return activecode;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }

}
