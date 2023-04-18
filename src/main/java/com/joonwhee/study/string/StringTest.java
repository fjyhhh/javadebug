package com.joonwhee.study.string;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * String的不可变性
 *
 * @author: 程序员囧辉
 * @date: 2022/5/11 23:04
 */
public class StringTest {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            2,
            2,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100));

    /**
     * 在编译期间，"jionghui"被加载到该类的运行时常量池，标记为#2（符号引用），
     * 代码中的两个"jionghui"此时都指向#2，当运行第一行代码时，ldc #2 会到常量池去找#2对应的项，
     * 如果该项还未解析，则此时会进行解析：在字符串常量池创建"jionghui"，并返回字符串常量池的引用（#2 -> ref）。
     * 而当运行到输出语句中的"jionghui"时，此时也会解析成字符串常量池中的引用，所以两个都是指向同一个对象，
     * 一个被修改了另一个自然也被修改了。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 例子1：反射修改string
//        testReflect();
        // 例子2：修改final定义的变量内容
        testModifyFinal();
        // 例子3：安全性
//        testSecurity();
    }

    private static void testReflect() throws NoSuchFieldException, IllegalAccessException {
        String name = "jionghui";
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        char[] value = (char[]) field.get(name);
        value[0] = 'a';
        System.out.println("jionghui" == name);
    }

    private static void testModifyFinal() {
        final char[] name =
                new char[]{'j', 'i', 'o', 'n', 'g'};
        // 无法赋值，会报错
//        name = new char[]{'h','u','i'};
        name[0] = 'z';
        System.out.println(name);
    }

    private static void testSecurity() throws IOException, InterruptedException {
        MyString myString = new MyString("jionghui");
        THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                dangerousOperation(myString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(100);
        myString.setValue("diaosi".toCharArray());
        System.in.read();
    }

    private static boolean securityCheck(MyString myString) {
        return Arrays.equals(myString.getValue(), "jionghui".toCharArray());
    }

    private static void dangerousOperation(MyString myString) throws InterruptedException {
        if (!securityCheck(myString)) {
            System.out.println("校验失败");
            return;
        }
        // 一些七的八的操作
        doSomething();
        // 执行危险操作
        dangerous(myString);
    }

    private static void dangerous(MyString myString) {
        System.out.println(myString);
    }

    private static void doSomething() throws InterruptedException {
        Thread.sleep(200);
    }

    public void testFinal() {
        String str = "程序员囧辉";
        str = "屌丝囧辉";
    }
}