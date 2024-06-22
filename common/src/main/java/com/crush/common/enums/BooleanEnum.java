package com.crush.common.enums;

/**
 * 布尔值枚举
 *
 * @author zhangxq
 * @date 2024/6/22
 */
public enum BooleanEnum {

    FALSE(0, "False"),

    TRUE(1, "TRUE"),

    ;

    private final Integer key;

    private final String val;

    BooleanEnum(Integer key, String val) {
        this.key = key;
        this.val = val;
    }

    public Integer getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }

    /**
     * 是否为 False
     *
     * @param e enum
     * @return 是否为 False
     */
    public static boolean isFalse(BooleanEnum e) {
        return FALSE.equals(e);
    }

    /**
     * 是否为 True
     *
     * @param e enum
     * @return 是否为 True
     */
    public static boolean isTrue(BooleanEnum e) {
        return TRUE.equals(e);
    }
}
