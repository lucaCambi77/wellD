package com.cambi.wellD.controller;

import com.cambi.wellD.model.Point;
import com.cambi.wellD.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wellD")
@RequiredArgsConstructor
public class WellDController {

    final SpaceService spaceService;

    @RequestMapping(value = "/point", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Point> addPoint(@RequestBody Point point) {
        spaceService.addPoint(point);
        return ResponseEntity.status(HttpStatus.CREATED).body(point);
    }

    @RequestMapping(value = "/space", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WellDSpaceResponse> space() {
        return ResponseEntity.ok(
                WellDSpaceResponse.builder().space(
                        spaceService.getSpace())
                        .build());
    }

    @RequestMapping(value = "/lines/{n}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WellDSegmentsResponse> lines(@PathVariable int n) {
        return ResponseEntity.ok(
                WellDSegmentsResponse.builder().segments(
                        spaceService.getLineSegments(n))
                        .build());
    }

    @RequestMapping(value = "/space", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WellDSpaceResponse> cleanSpace() {
        return ResponseEntity.ok(
                WellDSpaceResponse.builder().space(
                        spaceService.cleanSpace())
                        .build());
    }
}
