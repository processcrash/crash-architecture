package com.crush.common.pojo;

/**
 * 2D 坐标系 POJO
 *
 * @author zhangxq
 * @date 2024/6/22
 */
public class Point2D {

    private Double x;

    private Double y;

    public Point2D() {
    }

    public Point2D(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
