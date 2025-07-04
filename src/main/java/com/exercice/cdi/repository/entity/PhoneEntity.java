package com.exercice.cdi.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "phones")
public class PhoneEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long number;

  @Column(name = "citycode")
  private Integer cityCode;

  private String countryCode;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

}
