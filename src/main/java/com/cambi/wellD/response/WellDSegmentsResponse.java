package com.cambi.wellD.response;

import com.cambi.wellD.model.Point;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;
import java.util.Set;

@Value
@Builder
@Jacksonized
public class WellDSegmentsResponse {

    Map<String, Set<Point>> segments;
}
