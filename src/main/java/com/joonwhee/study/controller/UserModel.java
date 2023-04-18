package com.joonwhee.study.controller;

/**
 * @author: 程序员囧辉
 * @date: 2022/4/23 11:10
 */
public class UserModel {

    private long userId;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
