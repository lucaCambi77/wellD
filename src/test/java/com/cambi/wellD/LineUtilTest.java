package com.cambi.wellD;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LineUtilTest {

    @Test
    public void should_be_on_same_line() {

        Point point2 = new Point(1.0, 1.0);
        Point point1 = new Point(2.0, 2.0);

        Point point3 = new Point(0.0, 0.0);

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

        Point point2 = new Point(1.0, 2.0);
        Point point1 = new Point(2.0, 2.0);

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
}
