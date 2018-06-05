package ua.nure.tanasiuk.resource;


import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.nure.tanasiuk.dto.RouteDto;
import ua.nure.tanasiuk.dto.RouteRequest;
import ua.nure.tanasiuk.dto.Ticket;
import ua.nure.tanasiuk.model.Station;
import ua.nure.tanasiuk.service.RouteService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/routes", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "route", description = "Operations pertaining to routes")
@Validated
public class RouteResource {
    private final RouteService routeService;

    public RouteResource(RouteService routeService) {
        this.routeService = routeService;
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @GetMapping("/{id}")
    @Authorization("Bearer")
    public ResponseEntity<RouteDto> getById(@RequestParam Long requestInitiatorId,
                                            @PathVariable("id") Long id) {
        return ResponseEntity.ok(routeService.getById(id, requestInitiatorId));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @GetMapping("/history")
    @Authorization("Bearer")
    public ResponseEntity<List<RouteDto>> getHistory(@RequestParam Long requestInitiatorId) {
        return ResponseEntity.ok(routeService.getHistory(requestInitiatorId));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @GetMapping("/favorites")
    @Authorization("Bearer")
    public ResponseEntity<List<RouteDto>> getFavorites(@RequestParam Long requestInitiatorId) {
        return ResponseEntity.ok(routeService.getFavorites(requestInitiatorId));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @PostMapping
    @Authorization("Bearer")
    public ResponseEntity<RouteDto> create(@RequestParam Long requestInitiatorId,
                                           @RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(routeService.create(routeRequest, requestInitiatorId));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @PutMapping("/{id}")
    @Authorization("Bearer")
    public ResponseEntity<RouteDto> edit(@RequestParam Long requestInitiatorId,
                                         @PathVariable("id") Long id,
                                         @RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(routeService.edit(routeRequest, id, requestInitiatorId));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @PutMapping("/{id}/ticket")
    @Authorization("Bearer")
    public ResponseEntity replaceTicket(@RequestParam Long requestInitiatorId,
                                        @PathVariable("id") Long id,
                                        @RequestBody RouteDto route) {
        route.setId(id);
        routeService.replaceTicket(route, requestInitiatorId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @PutMapping("/{id}/favorite")
    @Authorization("Bearer")
    public ResponseEntity switchFavorite(@RequestParam Long requestInitiatorId,
                                         @PathVariable("id") Long id) {
        routeService.switchFavorite(id, requestInitiatorId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @GetMapping("/ticket/alternatives")
    @Authorization("Bearer")
    public ResponseEntity<List<Ticket>> getAlternatives(@RequestParam Long requestInitiatorId,
                                                        @PathVariable("id") int id) {
        return ResponseEntity.ok(routeService.getAlternatives(id));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @GetMapping("/stations")
    @Authorization("Bearer")
    public ResponseEntity<List<Station>> getStations(@RequestParam Long requestInitiatorId) {
        return ResponseEntity.ok(routeService.getStations());
    }
}
