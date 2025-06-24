package com.exercice.cdi.controller;

import com.exercice.cdi.controller.dto.LoginResponseDTO;
import com.exercice.cdi.controller.dto.SignUpRequestDTO;
import com.exercice.cdi.controller.dto.SignUpRespDTO;
import com.exercice.cdi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/bci")
public class LoginController {

  @Autowired
  LoginService loginService;

  @PostMapping("/sign-up")
  public ResponseEntity<SignUpRespDTO> signUp(@Valid @RequestBody SignUpRequestDTO request) throws Exception {
    SignUpRespDTO response = loginService.registrarUsuario(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestParam String token) throws Exception {
    LoginResponseDTO response = loginService.realizarLogin(token);
    return ResponseEntity.ok(response);
  }
}
