package com.crush.test;

/**
 * 断言测试类
 *
 * @author zhangxq
 * @since 2024/6/22
 */
public class Assertion {

    /**
     * 判断 val 布尔值是否为我们希望得到的值 wanted
     *
     * @param val 实际值
     * @param wanted 希望得到的值
     * @return true 是我们希望得到的结果，false 不是我们希望得到的结果
     */
    public static boolean isValWanted(boolean val, boolean wanted) {
        return val == wanted;
    }

    /**
     * 判断是否为 true， 若为 false 则报错。
     *
     * @param condition 条件表达式
     */
    public static void isTrue(boolean condition) {
        var flag = isValWanted(true, condition);
        if (!flag) {
            throw new TestException("Assertion Exception, The condition is false!");
        }
    }

    /**
     * 判断是否为 false，若为 true 则报错。
     *
     * @param condition 条件表达式
     */
    public static void isFalse(boolean condition) {
        isTrue(!condition);
    }
}
