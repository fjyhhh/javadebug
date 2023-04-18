package com.joonwhee.study.string;

import java.util.Arrays;

/**
 * @author: 程序员囧辉
 * @date: 2022/5/18 23:27
 */
public class MyString {

    private char[] value;

    public MyString() {
        this.value = new char[]{};
    }

    public MyString(char[] value) {
        this.value = value;
    }

    public MyString(String original) {
        this.value = original.toCharArray();
    }

    public char[] getValue() {
        return value;
    }

    public void setValue(char[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyString{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}
