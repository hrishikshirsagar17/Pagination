package com.zonions.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
public @Data class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String mimetype;

  @Lob
  @Column(name = "pic")
  private byte[] pic;

  private String type;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;


  @Column(name = "Created_At")
  private String createdAt;

  @Column(name = "Modified_At")
  private String modifiedAt;
}
