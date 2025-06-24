package com.exercice.cdi.dto

import com.exercice.cdi.controller.dto.SignUpRequestDTO
import spock.lang.Specification

import javax.validation.Validator
import javax.validation.Validation

class SignUpRequestDTOSpec extends Specification {

    Validator validator = Validation.buildDefaultValidatorFactory().validator

    def "valid DTO should pass validation"() {
        given:
        def dto = new SignUpRequestDTO(
                name: "Juan Pérez",
                email: "juan.perez@example.com",
                password: "Abcdef12"
        )

        when:
        def violations = validator.validate(dto)

        then:
        violations.isEmpty()
    }

    def "invalid email should fail validation"() {
        given:
        def dto = new SignUpRequestDTO(
                name: "Juan Pérez",
                email: "correo-invalido",
                password: "Abcdef12"
        )

        when:
        def violations = validator.validate(dto)

        then:
        violations.any { it.propertyPath.toString() == "email" }
    }

    def "invalid password should fail validation"() {
        given:
        def dto = new SignUpRequestDTO(
                name: "Juan Pérez",
                email: "juan.perez@example.com",
                password: "abcdef12" // sin mayúscula
        )

        when:
        def violations = validator.validate(dto)

        then:
        violations.any { it.propertyPath.toString() == "password" }
    }
}
