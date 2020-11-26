package com.cambi.wellD.controller;

import com.cambi.wellD.model.Point;
import com.cambi.wellD.response.WellDSegmentsResponse;
import com.cambi.wellD.response.WellDSpaceResponse;
import com.cambi.wellD.service.SpaceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wellD")
@RequiredArgsConstructor
public class WellDController {

    final SpaceService spaceService;

    @ApiOperation(value = "Add a point to the space", response = Point.class)
    @RequestMapping(value = "/point", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Point addPoint(@RequestBody Point point) {
        spaceService.addPoint(point);
        return point;
    }

    @ApiOperation(value = "Get all points in the space", response = WellDSpaceResponse.class)
    @RequestMapping(value = "/space", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public WellDSpaceResponse space() {
        return WellDSpaceResponse.builder()
                .space(spaceService.getSpace())
                .build();
    }

    @ApiOperation(value = "Get all line segments passing through at least N points. Note that a line segment should be a set of points", response = WellDSegmentsResponse.class)
    @RequestMapping(value = "/lines/{n}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public WellDSegmentsResponse lines(@PathVariable int n) {
        return WellDSegmentsResponse.builder()
                .segments(spaceService.getLineSegments(n))
                .build();
    }

    @ApiOperation(value = "Remove all points from the space", response = WellDSpaceResponse.class)
    @RequestMapping(value = "/space", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public WellDSpaceResponse cleanSpace() {
        return WellDSpaceResponse.builder()
                .space(spaceService.cleanSpace())
                .build();
    }
}
