package net.liang.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lianghuiyong on 2016/7/4.
 */
public class APPUser extends BmobObject {
    private String PhoneNumber;
    private String Password;
    private String Username;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
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
