package com.joonwhee.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 程序员囧辉
 * @date: 2022/4/23 11:10
 */
@RequestMapping("study")
@RestController
public class StudyController {

    @RequestMapping("getUserInfo")
    public Object getUserInfo(@RequestParam("userId")Long userId) {
        if (userId == null || userId <= 0) {
            return "illegal";
        }
        UserModel userModel = queryUserInfoByUserId(userId);
        if (userModel == null) {
            return "fail";
        }
        return userModel;
    }

    private UserModel queryUserInfoByUserId(Long userId) {
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        userModel.setName("name=" + userId);
        return userModel;
    }

    private UserModel fakeQueryUserInfoByUserId(Long userId) {
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        userModel.setName("fakeName=" + userId);
        return userModel;
    }
}
