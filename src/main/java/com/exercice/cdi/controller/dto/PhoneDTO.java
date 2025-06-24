package com.exercice.cdi.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhoneDTO {
  private Long number;
  @JsonProperty("citycode")
  private Integer cityCode;
  @JsonProperty("countrycode") //there is a typo in the documentation here. it says contry.
  private String countryCode;
}
