package com.cambi.wellD;

import lombok.Data;

@Data
public class Point {

    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
