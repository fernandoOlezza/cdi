package com.exercice.cdi.util

import spock.lang.Specification

class AESUtilSpec extends Specification {

    def "debería encriptar y desencriptar correctamente un texto"() {
        given:
        def textoOriginal = "prueba123"

        when:
        def textoEncriptado = AESUtil.encrypt(textoOriginal)

        then:
        textoEncriptado
        textoEncriptado != textoOriginal

        when:
        def textoDesencriptado = AESUtil.decrypt(textoEncriptado)

        then:
        textoDesencriptado == textoOriginal
    }

    def "debería lanzar una excepción si el texto encriptado es inválido"() {
        given:
        def textoInvalido = "textoQueNoEsValido"

        when:
        AESUtil.decrypt(textoInvalido)

        then:
        thrown(Exception)
    }
}
