package com.cambi.wellD.controller;

import com.cambi.wellD.model.Point;
import com.cambi.wellD.response.WellDSegmentsResponse;
import com.cambi.wellD.response.WellDSpaceResponse;
import com.cambi.wellD.service.SpaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
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

        Point response = wellDController.addPoint(point);
        verify(spaceService).addPoint(point);
        assertNotNull(response);
        assertEquals(point, response);
    }

    @Test
    public void should_invoke_get_space() {
        when(spaceService.getSpace()).thenReturn(new HashSet<>(Collections.singletonList(new Point(1.0, 1.0))));
        WellDSpaceResponse response = wellDController.space();
        verify(spaceService).getSpace();
        assertNotNull(response);
        assertTrue(response.getSpace().contains(new Point(1.0, 1.0)));
    }

    @Test
    public void should_invoke_get_segments() {

        when(spaceService.getLineSegments(anyInt())).thenReturn(
                new HashMap<>() {{
                    put("Equation", new HashSet<>(Collections.singletonList(new Point(1.0, 1.0))));
                }});

        WellDSegmentsResponse response = wellDController.lines(100);
        verify(spaceService).getLineSegments(100);
        assertNotNull(response);
        assertEquals(1, response.getSegments().size());
        assertEquals(response.getSegments().entrySet().iterator().next().getKey(), "Equation");
        assertTrue(response.getSegments().entrySet().iterator().next().getValue().contains(new Point(1.0, 1.0)));
    }

    @Test
    public void should_invoke_delete_space() {
        when(spaceService.cleanSpace()).thenCallRealMethod();
        WellDSpaceResponse response = wellDController.cleanSpace();
        verify(spaceService).cleanSpace();
        assertEquals(0, response.getSpace().size());
    }
}
