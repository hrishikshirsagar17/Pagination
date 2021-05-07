package com.zonions.dto;

import java.io.Serializable;
import javax.persistence.Lob;
import lombok.Data;

public @Data class MenuDto implements Serializable {

  private int id;

  private String name;

  @Lob
  private byte[] pic;

  private String type;
}
