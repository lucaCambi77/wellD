package com.cambi.wellD.service;

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
        space.add(point);
    }

    public Set<Point> getSpace() {
        return space;
    }

    public Map<String, Set<Point>> getLineSegments(int points) {
        return linesService.getLineSegments(points, space);
    }

    public void deleteSpace() {
        space = new HashSet<>();
    }
}
