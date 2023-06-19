package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;

import peaksoft.dto.restoran.RestaurantRequest;
import peaksoft.dto.restoran.RestaurantResponse;
import peaksoft.service.RestaurantService;


import java.util.List;
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantApi {
    private final RestaurantService restaurantServices;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "token", summary = "save")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantServices.saveRestaurant(restaurantRequest);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "token", summary = "get all restaurant")

    public List<RestaurantResponse> getAllRestaurant() {
        return restaurantServices.getAllRestaurant();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "token", summary = "find by restaurant by id")
    public RestaurantResponse findByRestaurantId(@PathVariable Long id) {
        return restaurantServices.getRestaurantById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "token", summary = "delete")

    public SimpleResponse deleteByRestaurantId(@PathVariable Long id) {
        return restaurantServices.deleteRestaurantById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "token", summary = "update")
    public SimpleResponse updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantServices.updateRestaurant(id, restaurantRequest);
    }

}
