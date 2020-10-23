package com.cambi.wellD.controller;

import com.cambi.wellD.model.Point;
import com.cambi.wellD.service.SpaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WellDControllerUnitTest {

    @InjectMocks
    private WellDController wellDController;

    @Mock
    private SpaceService spaceService;

    @Test
    public void should_invoke_add_points() {

        Point point = new Point(1.0, 2.0);

        ResponseEntity<Point> response = wellDController.addPoint(point);
        verify(spaceService, times(1)).addPoint(point);
        assertNotNull(response.getBody());
        assertEquals(point, response.getBody());
    }

    @Test
    public void should_invoke_get_space() {
        when(spaceService.getSpace()).thenReturn(new HashSet<>(Arrays.asList(new Point(1.0, 1.0))));
        ResponseEntity<WellDSpaceResponse> response = wellDController.space();
        verify(spaceService, times(1)).getSpace();
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getSpace().contains(new Point(1.0, 1.0)));
    }

    @Test
    public void should_invoke_get_segments() {

        when(spaceService.getLineSegments(anyInt())).thenReturn(
                new HashMap<>() {{
                    put("Equation", new HashSet<>(Arrays.asList(new Point(1.0, 1.0))));
                }});

        ResponseEntity<WellDSegmentsResponse> response = wellDController.lines(100);
        verify(spaceService, times(1)).getLineSegments(100);
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getSegments().size());
        assertTrue(response.getBody().getSegments().entrySet().iterator().next().getKey().equals("Equation"));
        assertTrue(response.getBody().getSegments().entrySet().iterator().next().getValue().contains(new Point(1.0, 1.0)));
    }

    @Test
    public void should_invoke_delete_space() {
        when(spaceService.cleanSpace()).thenCallRealMethod();
        ResponseEntity<WellDSpaceResponse> response = wellDController.cleanSpace();
        verify(spaceService, times(1)).cleanSpace();
        assertEquals(0, response.getBody().getSpace().size());
    }
}
