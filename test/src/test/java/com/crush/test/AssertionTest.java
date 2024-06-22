package com.crush.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhangxq
 * @date 2024/6/22
 */
class AssertionTest {

    @BeforeAll
    public static void beforeAll() {
        // System.out.println("AssertionTest 开始测试");
    }

    @Test
    public void testAssertTrue() {
        Assertion.isTrue(true);
        try {
            Assertion.isTrue(false);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

}