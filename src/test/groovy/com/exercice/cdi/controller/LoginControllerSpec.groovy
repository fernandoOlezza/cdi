package com.exercice.cdi.controller

import com.exercice.cdi.controller.dto.LoginResponseDTO
import com.exercice.cdi.controller.dto.SignUpRequestDTO
import com.exercice.cdi.controller.dto.SignUpRespDTO
import com.exercice.cdi.service.LoginService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class LoginControllerSpec extends Specification {

    def loginService = Mock(LoginService)
    def controller = new LoginController(loginService: loginService)

    def "signUp debe retornar respuesta del servicio"() {
        given:
        def request = new SignUpRequestDTO()
        def expectedResponse = new SignUpRespDTO()
        loginService.registrarUsuario(request) >> expectedResponse

        when:
        ResponseEntity<SignUpRespDTO> response = controller.signUp(request)

        then:
        response.statusCode.value() == 200
        response.body == expectedResponse
    }

    def "login debe retornar respuesta del servicio"() {
        given:
        def token = "mock-token"
        def expectedResponse = new LoginResponseDTO()
        loginService.realizarLogin(token) >> expectedResponse

        when:
        ResponseEntity<LoginResponseDTO> response = controller.login(token)

        then:
        response.statusCode.value() == 200
        response.body == expectedResponse
    }
}
