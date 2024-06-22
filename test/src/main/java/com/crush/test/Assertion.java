package com.crush.test;

/**
 * 断言测试类
 *
 * @author zhangxq
 * @date 2024/6/22
 */
public class Assertion {

    /**
     * 判断是否为 true， 若为 false 则报错！
     *
     * @param condition 条件表达式
     */
    public static void isTrue(boolean condition) {
        if (!condition) {
            throw new TestException("Assertion Exception, The condition is false!");
        }
    }

}
