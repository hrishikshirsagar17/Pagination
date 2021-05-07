package com.zonions.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

public @Data class RestaurantDto implements Serializable {

  private static final long serialVersionUID = -1454335894754839645L;

  private int id;

  private String restaurantName;

  private String address;

  private String phoneNo;

  private String open_time;

  private String close_time;

  private String status;

  private String updatedTime;

  private String isDining;

  private String isTakeAway;

  private String isHomeDelivery;

  private int hitCount;

  private int radius;

  List<MenuDto> menu;

}
