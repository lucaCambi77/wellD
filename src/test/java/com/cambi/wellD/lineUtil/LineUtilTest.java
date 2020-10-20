package com.cambi.wellD.lineUtil;

import com.cambi.wellD.model.Point;
import com.cambi.wellD.util.LineUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineUtilTest {

    @Test
    public void should_be_on_same_line() {

        Point point2 = new Point(1.0, 1.0);
        Point point1 = new Point(2.0, 2.0);

        Point point3 = new Point(0.0, 0.0);

        assertEquals("Y=X", LineUtil.getLineEquationFrom(point1, point2));
        assertEquals(LineUtil.getLineEquationFrom(point1, point2), LineUtil.getLineEquationFrom(point1, point3));
    }

    @Test
    public void should_be_on_same_vertical_line() {

        Point point2 = new Point(-5.0, 2.0);
        Point point1 = new Point(-5.0, 3.0);

        Point point3 = new Point(-5.0, 0.0);

        assertEquals("X=-5.0", LineUtil.getLineEquationFrom(point1, point2));
        assertEquals(LineUtil.getLineEquationFrom(point1, point2), LineUtil.getLineEquationFrom(point1, point3));
    }

    @Test
    public void should_be_on_same_horizontal_line() {

        Point point1 = new Point(2.0, 2.0);
        Point point2 = new Point(1.0, 2.0);

        Point point3 = new Point(3.0, 2.0);

        assertEquals("Y=2.0", LineUtil.getLineEquationFrom(point1, point2));
        assertEquals(LineUtil.getLineEquationFrom(point1, point2), LineUtil.getLineEquationFrom(point1, point3));
    }

    @Test
    public void should_not_be_on_same_line_two_point_are_vertical() {

        Point point1 = new Point(0.0, 0.0);
        Point point2 = new Point(0.0, 1.0);

        Point point3 = new Point(1.0, 2.0);

        assertNotEquals(LineUtil.getLineEquationFrom(point1, point2), LineUtil.getLineEquationFrom(point1, point3));
    }

    @Test
    public void should_not_be_on_same_line_two_point_are_horizontal() {

        Point point1 = new Point(0.0, 1.0);
        Point point2 = new Point(1.0, 1.0);

        Point point3 = new Point(1.0, 2.0);

        assertNotEquals(LineUtil.getLineEquationFrom(point1, point2), LineUtil.getLineEquationFrom(point1, point3));
    }

    @Test
    public void should_not_be_on_same_line_two_point_sparse() {

        Point point1 = new Point(0.0, 1.0);
        Point point2 = new Point(1.0, 2.0);

        Point point3 = new Point(-1.0, 2.0);

        assertNotEquals(LineUtil.getLineEquationFrom(point1, point2), LineUtil.getLineEquationFrom(point1, point3));
    }

    @Test
    public void check_decimal_round() {
        Point point1 = new Point(2.0, 2.0);
        Point point2 = new Point(-1.0, 1.0);
        assertEquals("Y=0.333X+1.333", LineUtil.getLineEquationFrom(point1, point2));
    }

    @Test
    public void check_Decimal_Zero_One_Double() {
        assertEquals(true, checkDecimals(0.0, 0));
        assertEquals(true, checkDecimals(0.000, 0));
        assertEquals(false, checkDecimals(0.001, 0));
        assertEquals(false, checkDecimals(1.001, 0));
        assertEquals(true, checkDecimals(1.000, 1));
        assertEquals(true, checkDecimals(1.0, 1));
    }

    private boolean checkDecimals(Double a, int b) {
        return a == b;
    }
}
