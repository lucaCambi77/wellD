package com.cambi.wellD.service;

import com.cambi.wellD.exception.WellDException;
import com.cambi.wellD.model.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpaceServiceTest {

    @InjectMocks
    private SpaceService spaceService;

    @Mock
    private LinesService linesService;

    @Test
    public void should_add_points() {
        Point point1 = new Point(1.0, 2.0);

        spaceService.addPoint(point1);

        Set<Point> space = spaceService.getSpace();
        assertEquals(1, space.size());
        assertEquals(point1, space.iterator().next());

        Point point2 = new Point(2.0, 2.0);

        spaceService.addPoint(point2);

        Set<Point> space1 = spaceService.getSpace();
        assertEquals(2, space1.size());

        assertTrue(space1.stream().anyMatch(p -> p.equals(point1)));
        assertTrue(space1.stream().anyMatch(p -> p.equals(point2)));

        Point point3 = new Point(2.0, 2.0);

        spaceService.addPoint(point3);
        assertEquals(2, spaceService.getSpace().size());
    }

    @Test
    public void should_delete_all_points() {

        Point point1 = new Point(1.0, 2.0);

        spaceService.addPoint(point1);

        assertEquals(1, spaceService.getSpace().size());

        spaceService.cleanSpace();

        Set<Point> space = spaceService.getSpace();

        assertEquals(0, space.size());
    }

    @Test
    public void should_find_all_lines() {

        int NPoints = 2;

        when(linesService.getLineSegments(anyInt(), anySet())).thenReturn(new HashMap<>() {{
            put("equation1", new HashSet<>(Arrays.asList(new Point(1.0, 2.0), new Point(2.0, 2.0))));
            put("equation2", new HashSet<>(Arrays.asList(new Point(0.0, 2.0), new Point(4.0, 2.0))));
        }});

        Map<String, Set<Point>> space = spaceService.getLineSegments(NPoints);

        verify(linesService).getLineSegments(NPoints, new HashSet<>());
        assertEquals(2, space.size());
    }

    @Test
    public void should_fail_points_add_invalid_input() {

        assertThrows(WellDException.class
                , () -> spaceService.addPoint(null)
                , "Should throw for null input");

        assertThrows(WellDException.class
                , () -> spaceService.addPoint(new Point(null, 1.0))
                , "Should throw for invalid input");

        assertThrows(WellDException.class
                , () -> spaceService.addPoint(new Point(1.0, null))
                , "Should throw for invalid input");
    }
}
