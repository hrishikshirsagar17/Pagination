package com.zonions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.zonions.entity.Menu;
import com.zonions.entity.Restaurant;
import com.zonions.repository.MenuRepository;
import com.zonions.repository.RestaurantRepository;

@Service
public class MenuService {

  @Autowired
  MenuRepository menuRepository;

  @Autowired
  RestaurantRepository restaurantRepository;

  private final static Logger logger = LoggerFactory.getLogger(MenuService.class);

  // Upload Menu
  public Optional<Menu> uploadMenu(MultipartFile file, int id, String type) {
    return restaurantRepository.findById(id).map(restaurant -> {
      Menu menu = new Menu();
      try {
        if (restaurant != null) {
          Restaurant rest = new Restaurant();
          rest.setId(restaurant.getId());
          rest.setRestaurantName(restaurant.getRestaurantName());
          menu.setRestaurant(rest);
          menu.setType(type);
          menu.setName(file.getOriginalFilename());
          menu.setMimetype(file.getContentType());
          menu.setPic(file.getBytes());

        }
      } catch (Exception e) {
        logger.error(e + "");
      }
      return menuRepository.save(menu);
    });
  }


  // Edit Menu
  public Menu editMenu(MultipartFile file, int id, String type) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    List<Menu> list = new ArrayList<Menu>();
    Menu menu = new Menu();
    try {
      if (!restaurant.isEmpty()) {
        Restaurant restaurant2 = restaurant.get();
        List<Menu> menu2 = restaurant2.getMenu();
        for (Menu m : menu2) {
          if (m.getType().equals(type))
            menuRepository.deleteById(m.getId());
        }
        menu.setRestaurant(restaurant2);
        menu.setType(type);
        menu.setName(file.getOriginalFilename());
        menu.setMimetype(file.getContentType());
        menu.setPic(file.getBytes());
        list.add(menu);
        restaurant2.setMenu(list);
      }
    } catch (Exception e) {
      logger.error(e + "");
    }
    return menuRepository.save(menu);
  }

}
