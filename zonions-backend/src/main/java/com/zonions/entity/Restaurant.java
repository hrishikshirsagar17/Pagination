package com.zonions.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Restaurant implements Serializable {

  private static final long serialVersionUID = 6665821165040459474L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "restaurant_name")
  private String restaurantName;
  @Column(name = "address")
  private String address;
  @Column(name = "phone_no")
  private String phoneNo;
  @Column(name = "open_time")
  private String open_time;
  @Column(name = "close_time")
  private String close_time;
  @Column(name = "status")
  private String status;
  @Column(name = "updated_time")
  private String updatedTime;
  @Column(name = "dining")
  private String isDining;
  @Column(name = "take_away")
  private String isTakeAway;
  @Column(name = "home_delivery")
  private String isHomeDelivery;
  @Column(name = "Created_At")
  private String createdAt;
  @Column(name = "Modified_At")
  private String modifiedAt;
  @Column(name = "hit_count")
  private int hitCount;
  private int radius;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = {CascadeType.MERGE})
  private List<Menu> menu;


  public Restaurant(String restaurantName, String address, String phoneNo, String open_time,
      String close_time, String status, String updatedTime, String isDining, String isTakeAway,
      String isHomeDelivery) {
    super();
    this.restaurantName = restaurantName;
    this.address = address;
    this.phoneNo = phoneNo;
    this.open_time = open_time;
    this.close_time = close_time;
    this.status = status;
    this.updatedTime = updatedTime;
    this.isDining = isDining;
    this.isTakeAway = isTakeAway;
    this.isHomeDelivery = isHomeDelivery;
  }



}

