package com.crush.test;

/**
 * 测试异常
 *
 * @author zhangxq
 * @date 2024/6/22
 */
public class TestException extends RuntimeException {

    public TestException() {
    }

    public TestException(String message) {
        super(message);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestException(Throwable cause) {
        super(cause);
    }

    public TestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
