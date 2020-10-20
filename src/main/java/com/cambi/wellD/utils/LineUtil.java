package com.cambi.wellD.utils;

import com.cambi.wellD.model.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LineUtil {

    public static String getLineEquationFrom(Point P, Point Q) {

        if (Q.getX() - P.getX() == 0)
            return "X=" + Q.getX();
        else if (Q.getY() - P.getY() == 0)
            return "Y=" + Q.getY();

        double slope = (Q.getY() - P.getY()) / (Q.getX() - P.getX());
 
        double roundedSlope = new BigDecimal(Double.toString(slope))
                .setScale(3, RoundingMode.HALF_UP).doubleValue();

        double c = (roundedSlope * -Q.getX()) - (-Q.getY());

        return "Y=" + (roundedSlope == 1 ? "" : roundedSlope) + "X"
                + (c == 0 ? "" : (Math.signum(c) == 1.0 ? "+" : "-")
                + c);
    }
}
