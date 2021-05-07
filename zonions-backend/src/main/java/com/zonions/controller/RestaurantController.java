package com.zonions.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zonions.dto.RestaurantDto;
import com.zonions.entity.Restaurant;
import com.zonions.service.RestaurantService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/zonions")
public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;

  // To add restaurant object
  @PostMapping("/restaurant")
  public ResponseEntity<Restaurant> save(@RequestBody Restaurant restaurant) {
    return restaurantService.createRestaurant(restaurant);
  }

  @GetMapping("/restaurant")
  public List<RestaurantDto> getAllRestaurant(@RequestParam("sortField") String sortField,
      @RequestParam("sortDir") String sortDir, @RequestParam("pageNumber") int pageNumber,
      @RequestParam("pageSize") int pageSize) {
    return restaurantService.getAllRestaurant(sortField, sortDir, pageNumber, pageSize);
  }

  @GetMapping("/restaurant/{id}")
  public RestaurantDto getRestaurantById(@PathVariable int id) {
    return restaurantService.getRestaurantById(id);
  }

  // To delete restaurant By Id
  @DeleteMapping("/restaurant/{id}")
  public ResponseEntity<HttpStatus> deleteByRestaurantId(@PathVariable int id) {
    return restaurantService.deleteById(id);
  }

  // To update Restaurant
  @PutMapping("/restaurant/{id}")
  public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable int id,
      @RequestBody Restaurant restaurant) {
    return restaurantService.updateRestaurant(id, restaurant);
  }


}
