package com.joonwhee.study.debug;

import com.joonwhee.study.spirit.SpiritAdvanceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 【debug的奇技淫巧】
 * debug：跟踪代码的运行，可以实时看到代码执行时的实时情况
 * debug的常见使用场景：
 * 1）需求代码测试：通过debug你才能知道你的代码究竟是怎么运行的，更容易发现问题
 * 2）问题排查：只要能进入到问题流程的debug，就没有排查不了的问题
 * 3）源码学习
 *
 * @author: 程序员囧辉
 * @date: 2022/4/23 11:10
 */
public class DebugDemo {

    /**
     * 基础DEBUG操作：
     * step over：往下运行一行
     * step into：进入方法内，自定义方法或三方库方法，JDK方法无法进入
     * force step into：强制进入方法内，一般step into进不去时可以使用
     * step out：退出方法，跟（force）step into 配合使用
     * Resume Program：恢复运行程序，运行到下一个断点的地方
     */
    private static void testBaseOperation() {
        System.out.println("start");
        int count = countNumber();
        System.out.println(count);
        System.out.println("end");
    }

    private static int countNumber() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count = count + i;
        }
        return count;
    }


    /**
     * 方法断点：方法入口（entry）和出口（exit）都会自动暂停
     * 常用场景：打在接口方法会自动跳到实现类，无需通过上下文环境去分析是哪个实现类
     * 缺点：可能会大大降低debug速度
     */
    private static void testMethodBreakpoint() {
        DebugServiceFactory debugServiceFactory = new DebugServiceFactory();
        DebugService debugService = debugServiceFactory.getDebugService();
        debugService.testMethodBreakpoint();
    }

    /**
     * 异常断点：在发生相应异常的地方暂停
     * 常用场景：程序抛出了异常，想快速定位是哪个地方抛出了异常
     * 缺点：异常可能会太多
     */
    private static void testExceptionBreakpoint() {
        try {
            Object obj = null;
            obj.getClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主动抛出异常
     * 常用场景：测试异常场景
     */
    private static void testThrowException() {
        return;
    }

    /**
     * 字段断点：在字段发生变更（默认）或者被访问（需要额外设置）时暂停
     * 常用场景：想知道某个属性在什么时候被赋值，从头开始调试太麻烦
     */
    private static void testFieldBreakpoint() {
        DebugModel debugModel = new DebugModel();
        debugModel.setUserId(4);
        System.out.println(debugModel);
    }

    /**
     * 降帧：返回到调用方法前
     * 常用场景：方法执行完想再重新执行一遍
     */
    private static void testDropFrame() {
        int i = 0;
        invokeMethod();
        System.out.println(i);
    }

    private static void invokeMethod() {
        System.out.println("程序员囧辉1");
        System.out.println("程序员囧辉2");
    }

    /**
     * 断点条件：在符合条件时才暂停
     * 常用场景：断点地方执行次数过多，避免浪费时间在不想关注的流程上，例如排查某个存在问题的spring bean
     */
    private static void testCondition() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count = count + i;
        }
    }

    /**
     * 强制返回：用于结束当前流程，直接返回
     * 常用场景：避免后续资源（数据库/缓存）操作
     */
    private static void testForceReturn() {
        int i = 0;
        System.out.println("接下去就是写数据库啦，大侠三思");
        writeDatabase();
    }

    private static void writeDatabase() {
        System.out.println("没想到吧，我还是执行了👿");
    }

    /**
     * stream调试：当代码进入stream调用链时，可以将stream的详细转换过程展示出来
     * 常用场景：stream流程过于复杂导致难以理解时使用
     */
    private static void testTraceCurrentStreamChain() {
        List<Integer> numberList = new ArrayList<>();
        numberList.add(null);
        for (int i = 0; i < 20; i++) {
            numberList.add(i);
        }
        List<String> stringList = numberList.stream()
                .filter(Objects::nonNull)
                .filter(DebugDemo::filterLteFive)
                .filter(i -> i % 2 == 0)
                .map(String::valueOf)
                .collect(Collectors.toList());
        System.out.println(stringList);
    }

    private static boolean filterLteFive(int i) {
        return i > 5;
    }

    /**
     * 执行表达式：高效调试神器。用于执行一段我们实时写的代码
     * 常用场景：查看数据、修改数据
     * 奇淫技巧：Evaluate Expression + Remote Debug = 线上问题排查神器
     */
    private static void testEvaluateExpression() {
        int age = getAge();
        if (age > 18) {
            System.out.println("岁月是把杀猪刀");
        } else {
            System.out.println("他还只是一个孩子啊");
        }
    }

    private static int getAge() {
        return 17 + ThreadLocalRandom.current().nextInt(50);
    }

    /**
     * 远程debug：线上问题排查神器，调试部署在远程服务器上的代码
     * 常用场景：线上问题排查
     * 配置参数：
     * JDK1.3 or earlier：-Xnoagent -Djava.compiler=NONE -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005
     * JDK1.4：-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005
     * JDK5-8：-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
     * JDK9 or later：-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
     * <p>
     * 配置方式：
     * 1）使用启动命令：
     * java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar java-study-demo-0.0.1-SNAPSHOT.jar
     * "C:\Program Files\Java\jdk1.8.0_301\bin\java.exe" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar java-study-demo-0.0.1-SNAPSHOT.jar
     * 2）在启动脚本中增加配置
     * <p>
     * 注意点：
     * 1）只能在内网环境使用，否则存在安全隐患
     * 2）本地代码最好和远程代码一致，否则你会不知道执行到哪里
     */
    private static void testRemoteDebug() {
    }

    /**
     * 多线程调试：避免debug到自己懵了。
     * Thread：暂停进入断点的线程，不影响其他线程执行。所有线程依次debug
     * All：暂停全部线程。只debug第一个暂停线程
     * 常用场景：多线程场景下，想跟踪每个线程的执行过程
     */
    private static void testSuspend() throws InterruptedException {
        new Thread(() -> {
            System.out.println("Thread 1 start");
            System.out.println("Thread 1 end");
        }).start();
        new Thread(() -> {
            System.out.println("Thread 2 start");
            System.out.println("Thread 2 end");
        }).start();
        System.out.println("main thread end");
        Thread.sleep(10000);
    }

    /**
     * 多线程调试：避免debug到自己懵了。
     * Thread：所有线程依次debug
     * All：只debug第一个暂停线程
     * 常用场景：多线程场景下，想跟踪每个线程的执行过程
     */
    private static void testSuspend2() throws InterruptedException {
        new Thread(() -> {
            System.out.println("Thread 1 start");
            System.out.println("Thread 1 end");
        }).start();
        new Thread(() -> {
            System.out.println("Thread 2 start");
            System.out.println("Thread 2 end");
        }).start();
        System.out.println("main thread end");
        Thread.sleep(10000);
    }

    public static void main(String[] args) throws Exception {
//        testBaseOperation();
        testMethodBreakpoint();
//        testFieldBreakpoint();
//        testExceptionBreakpoint();
//        testThrowException();
//        testDropFrame();
//        testCondition();
//        testForceReturn();
//        testTraceCurrentStreamChain();
//        testEvaluateExpression();
//        testRemoteDebug();
//        testSuspend();
//        testSuspend2();
//        SpiritAdvanceUtils.debugAdvance();
    }
}
