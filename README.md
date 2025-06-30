# Proyecto BCI - API de Registro y Autenticación

Este proyecto es una API RESTful desarrollada con Spring Boot que permite el registro y autenticación de usuarios. Incluye validación de datos, manejo global de excepciones, pruebas unitarias con Spock y cobertura de código con JaCoCo.

---

## 🚀 Tecnologías utilizadas

- **Java 11**
- **Spring Boot 2.5.14**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate Validator**
- **H2 Database** (para pruebas)
- **JWT (JJWT 0.9.1)** para autenticación
- **Lombok**
- **Groovy 3.0.9**
- **Spock Framework 2.0**
- **JaCoCo** para cobertura de código

---

## ⚙️ Requisitos

- Java 11
- Gradle 7.x o superior
- IDE compatible con Spring Boot (IntelliJ, Eclipse, VS Code)

---

## 🧰 Herramientas recomendadas

- IntelliJ IDEA
- Postman o Insomnia para probar la API
- JaCoCo Plugin para IntelliJ

---

## Reporte covertura

- ./gradlew jacocoTestReport
- build/reports/jacoco/test/html/index.html

---

## Ejecutar el proyecto

- ./gradlew bootRun
- http://localhost:8080/v1/bci

---

## 🧩 Endpoints principales

- POST /v1/bci/sign-up — Registro de usuario
- POST /v1/bci/login — Autenticación con token

---
