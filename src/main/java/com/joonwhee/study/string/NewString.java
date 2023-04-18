package com.joonwhee.study.string;

/**
 * 流程图详解 new String("abc") 创建了几个字符串对象
 *
 * @author: 程序员囧辉
 * @date: 2022/5/13 22:42
 */
public class NewString {

    /**
     * 查看字节码插件：jclasslib Bytecode Viewer
     * ldc：将int、float或String型常量值从常量池中推送至栈顶
     *
     * @param args
     */
    public static void main(String[] args) {

        // 例子1、创建几个字符串对象：1个或2个字符串对象
        // 如果字符串常量池中不存在jionghui（equals比较），则在字符串常量池中创建1个对象（对象还是在堆中创建，字符串常量池只放引用），
        // new String会创建一个对象
//        String str1 = new String("jionghui");

        // 例子2、创建几个字符串对象
//        String str2 = "jionghui";
//        System.out.println(str1 == str2);

        // 例子3、字符串拼接
//        String str3 = "jiong" + "hui";

        // 例子4、字符串拼接
//        String str4 = new String("jiong") + "hui";
//        System.out.println(str3 == str4);

        // intern：如果常量池中存在当前字符串, 则返回常量池中的字符串。否则, 将该字符串放入常量池，然后返回该字符串对象的引用。
        // 字面量会对应该类的运行时常量池的一个符号引用，当具体执行到该行代码时会去解析该符号引用：到字符串常量池中判断是否存在，
        // 如果是则返回常量池中的引用，否则在堆中新建对象，并将该对象的引用放在字符串常量池，然后返回引用

        // 例子5、intern例子1
//        String str5 = new String("1") + new String("1");
//        str5.intern();
//        String str6 = "11";
//        System.out.println(str5 == str6);

        // 例子6、intern例子2
        String str7 = new String("1") + new String("1");
        String str8 = "11";
        String str9 = str7.intern();
        System.out.println(str7 == str8);
        System.out.println(str8 == str9);
    }
}
