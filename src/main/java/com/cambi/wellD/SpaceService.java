package com.cambi.wellD;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public Set<Point> getLines(int points) {
        return linesService.getLines(points);
    }

    public void deleteSpace() {
        space = new HashSet<>();
    }
}
