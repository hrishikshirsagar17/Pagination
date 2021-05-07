package com.zonions.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.zonions.dto.MenuDto;
import com.zonions.dto.RestaurantDto;
import com.zonions.entity.Menu;
import com.zonions.entity.Restaurant;
import com.zonions.repository.RestaurantRepository;

@Service
public class RestaurantService {


  @Autowired
  RestaurantRepository restaurantRepository;

  String status = "open";
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  LocalDateTime now = LocalDateTime.now();
  LocalTime localTime = LocalTime.now();
  String time = localTime.toString();
  private final AtomicInteger counter = new AtomicInteger(0);

  // Service To add restaurant
  public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
    try {
      if (restaurant.getIsDining().equalsIgnoreCase("true")) {
        restaurant.setIsDining("Available");
      } else {
        restaurant.setIsDining("Unavailable");
      }
      if (restaurant.getIsTakeAway().equalsIgnoreCase("true")) {
        restaurant.setIsTakeAway("Available");
      } else {
        restaurant.setIsTakeAway("Unavailable");
      }
      if (restaurant.getIsHomeDelivery().equalsIgnoreCase("true")) {
        restaurant.setIsHomeDelivery("Available");
      } else {
        restaurant.setIsHomeDelivery("Unavailable");
      }
      Restaurant _restaurant = new Restaurant(restaurant.getRestaurantName(),
          restaurant.getAddress(), restaurant.getPhoneNo(), restaurant.getOpen_time(),
          restaurant.getClose_time(), status, time, restaurant.getIsDining(),
          restaurant.getIsTakeAway(), restaurant.getIsHomeDelivery());
      _restaurant.setCreatedAt(dtf.format(now));
      restaurantRepository.save(_restaurant);

      return new ResponseEntity<>(_restaurant, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Service to get all restaurants
  public List<RestaurantDto> getAllRestaurant(String sortField, String sortDir, int pageNumber,
      int pageSize) {

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
        : Sort.by(sortField).descending();
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

    Page<Restaurant> page = restaurantRepository.findAll(pageable);

    RestaurantDto dto = null;
    MenuDto menuDto = null;
    List<RestaurantDto> list1 = new ArrayList<RestaurantDto>();
    List<MenuDto> menu = new ArrayList<MenuDto>();
    for (Restaurant rest : page) {
      dto = new RestaurantDto();
      menuDto = new MenuDto();
      System.out.println("rest id: " + rest.getId());
      dto.setId(rest.getId());
      dto.setRestaurantName(rest.getRestaurantName());
      dto.setAddress(rest.getAddress());
      dto.setOpen_time(rest.getOpen_time());
      dto.setClose_time(rest.getClose_time());
      dto.setStatus(rest.getStatus());
      dto.setUpdatedTime(rest.getUpdatedTime());
      dto.setIsDining(rest.getIsDining());
      dto.setIsTakeAway(rest.getIsTakeAway());
      dto.setIsHomeDelivery(rest.getIsHomeDelivery());
      dto.setHitCount(rest.getHitCount());
      for (Menu m : rest.getMenu()) {
        menuDto.setId(m.getId());
        menuDto.setName(m.getName());
        menuDto.setType(m.getType());
        menuDto.setPic(m.getPic());
      }
      menu.add(menuDto);
      dto.setMenu(menu);
      list1.add(dto);
    }

    return list1;
  }

  public Page<Restaurant> findPaginated(String pageNo, String pageSize, String sortField,
      String sortDirection) {
    int pageNumber = Integer.parseInt(pageNo);
    int pagesize = Integer.parseInt(pageSize);
    Sort sort =
        sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
            : Sort.by(sortField).descending();

    Pageable pageable = PageRequest.of(pageNumber - 1, pagesize, sort);
    return this.restaurantRepository.findAll(pageable);
  }

  // Get Restaurant By Id
  public RestaurantDto getRestaurantById(@PathVariable int id) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    RestaurantDto restaurantDto = new RestaurantDto();
    if (restaurant.isPresent()) {
      Restaurant restaurantData = restaurant.get();

      MenuDto menuDto = new MenuDto();
      restaurantDto.setId(restaurantData.getId());
      restaurantDto.setRestaurantName(restaurantData.getRestaurantName());
      restaurantDto.setAddress(restaurantData.getAddress());
      List<Menu> menu = restaurantData.getMenu();
      List<MenuDto> menuDto1 = new ArrayList<MenuDto>();
      for (Menu m : menu) {
        menuDto.setId(m.getId());
        menuDto.setName(m.getName());
        menuDto.setType(m.getType());
        menuDto.setPic(m.getPic());
      }
      menuDto1.add(menuDto);
      restaurantDto.setMenu(menuDto1);
    }
    return restaurantDto;
  }

  // Service to delete a restaurant
  public ResponseEntity<HttpStatus> deleteById(@PathVariable int id) {
    try {
      restaurantRepository.deleteById(id);
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Service to update a restaurant
  public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable int id,
      @RequestBody Restaurant restaurant) {
    Optional<Restaurant> data = restaurantRepository.findById(id);
    if (data.isPresent()) {
      Restaurant _restaurant = data.get();
      RestaurantDto restaurantDto = new RestaurantDto();

      _restaurant.setRestaurantName(restaurant.getRestaurantName());
      _restaurant.setAddress(restaurant.getAddress());
      _restaurant.setPhoneNo(restaurant.getPhoneNo());
      _restaurant.setOpen_time(restaurant.getOpen_time());
      _restaurant.setClose_time(restaurant.getClose_time());
      _restaurant.setStatus(restaurant.getStatus());
      _restaurant.setIsDining(_restaurant.getIsDining());
      _restaurant.setIsHomeDelivery(_restaurant.getIsHomeDelivery());
      _restaurant.setIsTakeAway(_restaurant.getIsTakeAway());
      _restaurant.setUpdatedTime(dtf.format(now));
      _restaurant.setModifiedAt(dtf.format(now));
      _restaurant.setHitCount(restaurant.getHitCount());
      restaurantDto.setId(restaurant.getId());
      restaurantDto.setRestaurantName(restaurant.getRestaurantName());
      restaurantDto.setAddress(restaurant.getAddress());
      restaurantDto.setPhoneNo(restaurant.getPhoneNo());
      restaurantDto.setOpen_time(restaurant.getOpen_time());
      restaurantDto.setClose_time(restaurant.getClose_time());
      restaurantDto.setStatus(restaurant.getStatus());
      restaurantDto.setIsDining(restaurant.getIsDining());
      restaurantDto.setIsHomeDelivery(restaurant.getIsHomeDelivery());
      restaurantDto.setIsTakeAway(restaurant.getIsTakeAway());
      restaurantDto.setHitCount(restaurant.getHitCount());
      List<Menu> list = restaurant.getMenu();
      List<MenuDto> list1 = new ArrayList<MenuDto>();
      MenuDto menuDto = new MenuDto();
      for (Menu m : list) {
        menuDto.setId(m.getId());
        menuDto.setName(m.getName());
        menuDto.setType(m.getType());
        menuDto.setPic(m.getPic());
      }
      list1.add(menuDto);
      restaurantDto.setMenu(list1);
      restaurantRepository.save(_restaurant);
      return new ResponseEntity<RestaurantDto>(restaurantDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


}
