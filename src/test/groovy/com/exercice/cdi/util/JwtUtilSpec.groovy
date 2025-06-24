package com.exercice.cdi.util

import io.jsonwebtoken.JwtException
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

class JwtUtilSpec extends Specification {

    def "deberia generar y extraer el token desde el email"() {
        given:
        def jwtUtil = new JwtUtil()
        def secretKey = "testSecretKey1234567890"
        ReflectionTestUtils.setField(jwtUtil, "secretKey", secretKey)

        def email = "prueba@email.com"

        when:
        def token = jwtUtil.generateToken(email)

        then:
        token != null
        token instanceof String

        when:
        def extractedEmail = jwtUtil.extractEmail(token)

        then:
        extractedEmail == email
    }

    def "deberia tirar una exception"() {
        given:
        def jwtUtil = new JwtUtil()
        def secretKey = "testSecretKey1234567890"
        ReflectionTestUtils.setField(jwtUtil, "secretKey", secretKey)

        def invalidToken = "invalid.token.value"

        when:
        jwtUtil.extractEmail(invalidToken)

        then:
        thrown(JwtException)
    }
}
