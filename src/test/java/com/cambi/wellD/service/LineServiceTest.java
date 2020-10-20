package com.cambi.wellD.service;

import com.cambi.wellD.exception.LineException;
import com.cambi.wellD.model.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class LineServiceTest {

    @InjectMocks
    private LinesService linesService;

    @Test
    public void should_throw_when_space_size_less_two() {

        Set<Point> input = new HashSet<>();
        input.add(new Point(1.0, 2.0));

        assertThrows(LineException.class
                , () -> linesService.getLineSegments(2, input)
                , "Should throw exception when space size less than 2");
    }

    @Test
    public void should_find_correct_segments_two_points_space_size_2() {

        Set<Point> input = new HashSet<>();
        input.add(new Point(1.0, 2.0));
        input.add(new Point(2.0, 3.0));

        Map<String, Set<Point>> map = linesService.getLineSegments(2, input);

        assertEquals(1, map.size());
        assertEquals("Y=X+1.0", map.entrySet().iterator().next().getKey());
    }

    @Test
    public void should_find_correct_segments_two_points_space_size_3() {

        Set<Point> input = new HashSet<>();
        input.add(new Point(1.0, 1.0));
        input.add(new Point(2.0, 2.0));
        input.add(new Point(-1.0, 1.0));

        Map<String, Set<Point>> map = linesService.getLineSegments(2, input);

        assertEquals(3, map.size());
        assertEquals(true, map.entrySet().stream().anyMatch(e -> e.getKey().equals("Y=X")));
        assertEquals(true, map.entrySet().stream().anyMatch(e -> e.getKey().equals("Y=1.0")));
        assertEquals(true, map.entrySet().stream().anyMatch(e -> e.getKey().equals("Y=0.333X+1.333")));
    }

    @Test
    public void should_not_find_correct_segments_three_points() {

        Set<Point> input = new HashSet<>();
        input.add(new Point(1.0, 1.0));
        input.add(new Point(2.0, 2.0));
        input.add(new Point(-1.0, 1.0));

        Map<String, Set<Point>> map = linesService.getLineSegments(3, input);

        assertEquals(0, map.size());
    }

    @Test
    public void should_find_correct_segments_three_points_on_a_line() {

        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(2.0, 2.0);
        Point p3 = new Point(3.0, 3.0);
        Point p4 = new Point(-1.0, 1.0);

        Set<Point> input = new HashSet<>();
        input.add(p1);
        input.add(p2);
        input.add(p3);
        input.add(p4);

        Map<String, Set<Point>> map = linesService.getLineSegments(3, input);

        assertEquals(1, map.size());
        assertEquals(true, map.entrySet().stream().anyMatch(e -> e.getKey().equals("Y=X")));

        Set<Point> set = map.entrySet().iterator().next().getValue();

        assertEquals(3, set.size());
        assertEquals(true, set.containsAll(Arrays.asList(p1, p2, p3)));
    }
}
