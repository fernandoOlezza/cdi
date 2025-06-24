package com.exercice.cdi.service.impl

import com.exercice.cdi.controller.dto.LoginResponseDTO
import com.exercice.cdi.controller.dto.SignUpRequestDTO
import com.exercice.cdi.controller.dto.SignUpRespDTO
import com.exercice.cdi.exception.EmailDuplicadoException
import com.exercice.cdi.exception.UserNotFoundException
import com.exercice.cdi.repository.UserRepository
import com.exercice.cdi.repository.entity.UserEntity
import com.exercice.cdi.util.AESUtil
import com.exercice.cdi.util.JwtUtil
import spock.lang.Specification

import java.time.LocalDateTime

class LoginServiceImplSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def jwtUtil = Mock(JwtUtil)
    def loginService = new LoginServiceImpl(userRepository: userRepository, jwtUtil: jwtUtil)

    def "debería registrar y devolver un objeto SignUpRespDTO"() {
        given:
        def request = new SignUpRequestDTO(
                name: "Juan Perez",
                email: "john@example.com",
                password: "password123",
                phones: []
        )

        userRepository.findByEmail("john@example.com") >> Optional.empty()
        jwtUtil.generateToken("john@example.com") >> "token-mokeado"

        when:
        def result = loginService.registrarUsuario(request)

        then:
        1 * userRepository.save(_ as UserEntity) >> { UserEntity user ->
            assert user.name == "Juan Perez"
            assert user.email == "john@example.com"
            assert user.token == "token-mokeado"
            return user
        }

        result instanceof SignUpRespDTO
        result.token == "token-mokeado"
        result.isActive
        result.created != null
    }

    def "debería lanzar EmailDuplicadoException si el email ya existe"() {
        given:
        def request = new SignUpRequestDTO(
                name: "Juan Perez",
                email: "john@example.com",
                password: "password123",
                phones: []
        )

        userRepository.findByEmail("john@example.com") >> Optional.of(new UserEntity())

        when:
        loginService.registrarUsuario(request)

        then:
        thrown(EmailDuplicadoException)
    }

    def "debería hacer login y retornar LoginResponseDTO"() {
        given:
        def token = "token-valido"
        def email = "john@example.com"
        def user = new UserEntity(
                id: UUID.randomUUID(),
                name: "Juan Perez",
                email: email,
                password: AESUtil.encrypt("password123"),
                created: LocalDateTime.now(),
                token: token,
                isActive: true,
                phones: []
        )

        jwtUtil.extractEmail(token) >> email
        userRepository.findByEmail(email) >> Optional.of(user)

        when:
        def result = loginService.realizarLogin(token)

        then:
        1 * userRepository.save(_ as UserEntity)
        result instanceof LoginResponseDTO
        result.name == "Juan Perez"
        result.enamil == email
        result.password == "password123"
        result.token == token
    }

    def "debería lanzar UserNotFoundException si el usuario no existe"() {
        given:
        def token = "token-invalido"
        def email = "notfound@example.com"

        jwtUtil.extractEmail(token) >> email
        userRepository.findByEmail(email) >> Optional.empty()

        when:
        loginService.realizarLogin(token)

        then:
        thrown(UserNotFoundException)
    }
}
