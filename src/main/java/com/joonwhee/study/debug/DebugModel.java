package com.joonwhee.study.debug;

/**
 * @author: 程序员囧辉
 * @date: 2022/4/23 11:10
 */
public class DebugModel {

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DebugModel{" +
                "userId=" + userId +
                '}';
    }
}
