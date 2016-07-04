package net.liang.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lianghuiyong on 2016/7/4.
 */
public class _User extends BmobObject {
    private String PhotoNumber;
    private String Email;
    private String Password;
    private String Username;

    public String getPhotoNumber() {
        return PhotoNumber;
    }

    public void setPhotoNumber(String photoNumber) {
        PhotoNumber = photoNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
