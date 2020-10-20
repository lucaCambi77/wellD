package com.cambi.wellD.controller;

import com.cambi.wellD.model.Point;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class WellDSpaceResponse {

    final Set<Point> space;
}
