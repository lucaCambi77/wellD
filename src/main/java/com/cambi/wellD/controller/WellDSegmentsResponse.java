package com.cambi.wellD.controller;

import com.cambi.wellD.model.Point;
import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.Set;

@Value
@Builder
public class WellDSegmentsResponse {

    final Map<String, Set<Point>> segments;
}
