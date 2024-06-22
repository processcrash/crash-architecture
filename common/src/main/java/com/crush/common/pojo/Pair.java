package com.crush.common.pojo;

/**
 * 一对数据 POJO
 *
 * @author zhangxq
 * @date 2024/6/22
 */
public class Pair<F, S> {

    private F first;

    private S second;

    public Pair() {
    }

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
}
