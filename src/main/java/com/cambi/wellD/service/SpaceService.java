package com.cambi.wellD.service;

import com.cambi.wellD.exception.WellDException;
import com.cambi.wellD.model.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SpaceService {

    private Set<Point> space = new HashSet<>();

    private final LinesService linesService;

    public void addPoint(Point point) {
        if (null == point || point.getX() == null || point.getY() == null)
            throw new WellDException("Input point should be valid e.g. -> {\"x\"=0.0, \"y\"=0.0}");

        space.add(point);
    }

    public Set<Point> getSpace() {
        return space;
    }

    public Map<String, Set<Point>> getLineSegments(int points) {
        return linesService.getLineSegments(points, space);
    }

    public Set<Point> cleanSpace() {
        space = new HashSet<>();
        return space;
    }
}
