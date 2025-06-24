package com.exercice.cdi.util

import com.exercice.cdi.controller.dto.PhoneDTO
import com.exercice.cdi.repository.entity.PhoneEntity
import com.exercice.cdi.repository.entity.UserEntity
import spock.lang.Specification

class PhoneMapperSpec extends Specification {

    def "mapea PhoneDTO a PhoneEntity"() {
        given:
        def dto = new PhoneDTO(number: 123456789L, cityCode: 1, countryCode: "+1")
        def user = new UserEntity()
        user.setId(UUID.randomUUID())

        when:
        def entity = PhoneMapper.toEntity(dto, user)

        then:
        entity.number == dto.number
        entity.cityCode == dto.cityCode
        entity.countryCode == dto.countryCode
        entity.user == user
    }

    def "mapea lista de PhoneDTOs a PhoneEntities"() {
        given:
        def dtos = [
                new PhoneDTO(number: 123L, cityCode: 1, countryCode: "+1"),
                new PhoneDTO(number: 456L, cityCode: 2, countryCode: "+44")
        ]
        def user = new UserEntity()
        user.setId(UUID.randomUUID())

        when:
        def entities = PhoneMapper.toEntityList(dtos, user)

        then:
        entities.size() == 2
        entities[0].number == 123L
        entities[1].cityCode == 2
        entities.every { it.user == user }
    }

    def "mapea PhoneEntity a PhoneDTO"() {
        given:
        def entity = new PhoneEntity(number: 789L, cityCode: 3, countryCode: "+33")

        when:
        def dto = PhoneMapper.toDTO(entity)

        then:
        dto.number == entity.number
        dto.cityCode == entity.cityCode
        dto.countryCode == entity.countryCode
    }

    def "mapea lista de PhoneEntities a PhoneDTOs"() {
        given:
        def entities = [
                new PhoneEntity(number: 111L, cityCode: 4, countryCode: "+49"),
                new PhoneEntity(number: 222L, cityCode: 5, countryCode: "+81")
        ]

        when:
        def dtos = PhoneMapper.toDTOList(entities)

        then:
        dtos.size() == 2
        dtos[0].number == 111L
        dtos[1].countryCode == "+81"
    }
}
