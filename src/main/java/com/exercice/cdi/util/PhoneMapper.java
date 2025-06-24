package com.exercice.cdi.util;

import com.exercice.cdi.controller.dto.PhoneDTO;
import com.exercice.cdi.repository.entity.PhoneEntity;
import com.exercice.cdi.repository.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneMapper {

  public static PhoneEntity toEntity(PhoneDTO dto, UserEntity user) {
    PhoneEntity entity = new PhoneEntity();
    entity.setNumber(dto.getNumber());
    entity.setCityCode(dto.getCityCode());
    entity.setCountryCode(dto.getCountryCode());
    entity.setUser(user);
    return entity;
  }

  public static List<PhoneEntity> toEntityList(List<PhoneDTO> dtos, UserEntity user) {
    return dtos.stream()
        .map(dto -> toEntity(dto, user))
        .collect(Collectors.toList());
  }

  public static PhoneDTO toDTO(PhoneEntity entity) {
    PhoneDTO dto = new PhoneDTO();
    dto.setNumber(entity.getNumber());
    dto.setCityCode(entity.getCityCode());
    dto.setCountryCode(entity.getCountryCode());
    return dto;
  }

  public static List<PhoneDTO> toDTOList(List<PhoneEntity> entities) {
    return entities.stream()
        .map(PhoneMapper::toDTO)
        .collect(Collectors.toList());
  }
}
