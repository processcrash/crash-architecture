package com.crush.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhangxq
 * @since 2024/6/22
 */
class AssertionTest {

    @BeforeAll
    public static void beforeAll() {
        // System.out.println("AssertionTest 开始测试");
    }

    @Test
    public void testIsValWanted() {
        var res11 = Assertion.isValWanted(true, true);
        assertTrue(res11);
        var res10 = Assertion.isValWanted(true, false);
        assertFalse(res10);
        var res01 = Assertion.isValWanted(false, true);
        assertFalse(res01);
        var res00 = Assertion.isValWanted(false, false);
        assertTrue(res00);
    }

    @Test
    public void testIsTrue() {
        Assertion.isTrue(true);
        try {
            Assertion.isTrue(false);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIsFalse() {
        Assertion.isFalse(false);
        try {
            Assertion.isFalse(true);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

}