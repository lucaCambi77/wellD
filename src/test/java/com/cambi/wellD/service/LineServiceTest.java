package com.cambi.wellD.service;

import com.cambi.wellD.LineException;
import com.cambi.wellD.LinesService;
import com.cambi.wellD.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

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
}
