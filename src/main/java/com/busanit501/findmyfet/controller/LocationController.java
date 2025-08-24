// src/main/java/com/busanit501/findmyfet/controller/LocationController.java
package com.busanit501.findmyfet.controller;

import com.busanit501.findmyfet.domain.Location;
import com.busanit501.findmyfet.dto.LocationDto;
import com.busanit501.findmyfet.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> saveLocation(@RequestBody LocationDto locationDto) {
        // LocationDto를 Service로 전달하고, Service에서 반환된 엔티티를 다시 DTO로 변환
        Location newLocation = locationService.saveLocation(locationDto);
        LocationDto newLocationDto = new LocationDto();
        newLocationDto.setLocationName(newLocation.getLocationName());
        newLocationDto.setLatitude(newLocation.getLatitude());
        newLocationDto.setLongitude(newLocation.getLongitude());

        return ResponseEntity.ok(newLocationDto);
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        // 엔티티 리스트를 DTO 리스트로 변환하여 반환
        List<LocationDto> locationDtos = locations.stream()
                .map(location -> {
                    LocationDto dto = new LocationDto();
                    dto.setLocationName(location.getLocationName());
                    dto.setLatitude(location.getLatitude());
                    dto.setLongitude(location.getLongitude());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(locationDtos);
    }
}