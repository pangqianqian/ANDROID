package com.example.pangqianqian.phone_book;

/**
 * Created by pangqianqian on 2017/11/19.
 */


//有关联系人信息的类
public class PhoneInfo {

    private String name;
    private String number;

    public PhoneInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
