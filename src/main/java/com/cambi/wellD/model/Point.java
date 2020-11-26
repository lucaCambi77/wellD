package com.cambi.wellD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@Data
@Jacksonized
public class Point {

    final Double x;
    final Double y;
}
