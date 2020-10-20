package com.cambi.wellD.service;

import com.cambi.wellD.exception.WellDException;
import com.cambi.wellD.model.Point;
import com.cambi.wellD.utils.LineUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LinesService {

    public Map<String, Set<Point>> getLineSegments(int points, Set<Point> input) {

        if (input.size() < 2)
            throw new WellDException("Input size must be greater than 2");

        if (points < 2)
            throw new WellDException("Lines should match more than 1 point");

        Map<String, Set<Point>> map = new HashMap<>();

        List<Point> list = new ArrayList<>(input);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String equation = LineUtil.getLineEquationFrom(list.get(i), list.get(j));
                Set<Point> pointsOnSameLine = map.getOrDefault(equation, new HashSet<>());
                pointsOnSameLine.add(list.get(i));
                pointsOnSameLine.add(list.get(j));
                map.put(equation, pointsOnSameLine);
            }
        }

        return map.entrySet().stream()
                .filter(p -> p.getValue().size() >= points)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }
}
