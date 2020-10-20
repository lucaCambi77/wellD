package com.cambi.wellD;

public class LineUtil {

    public static String getLineEquationFrom(Point P, Point Q) {

        if (Q.getX() - P.getX() == 0)
            return "X=" + Q.getX();
        else if (Q.getY() - P.getY() == 0)
            return "Y=" + Q.getY();

        double slope = (Q.getY() - P.getY()) / (Q.getX() - P.getX());

        double c = (slope * -Q.getX()) - (-Q.getY());

        return "Y=" + slope + "X" + (Math.signum(c) == 1.0 ? "+" : "-") + c;
    }
}
