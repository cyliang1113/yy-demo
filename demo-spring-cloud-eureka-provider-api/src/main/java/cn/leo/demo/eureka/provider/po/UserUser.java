package cn.leo.demo.eureka.provider.po;

import java.io.Serializable;

public class UserUser implements Serializable {


    private static final long serialVersionUID = -2429274662552243328L;

    private String Username;
    private String password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
