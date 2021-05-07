package com.zonions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zonions.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

  // public Optional<Menu> findByRestaurantidAndType(int restaurantid, String type);

  // public List<Menu> findByRestaurantid(int restaurantid);

}
