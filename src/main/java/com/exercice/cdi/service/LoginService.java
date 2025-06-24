package com.exercice.cdi.service;

import com.exercice.cdi.controller.dto.LoginResponseDTO;
import com.exercice.cdi.controller.dto.SignUpRequestDTO;
import com.exercice.cdi.controller.dto.SignUpRespDTO;

public interface LoginService {
  SignUpRespDTO registrarUsuario(SignUpRequestDTO request) throws Exception;

  LoginResponseDTO realizarLogin(String token) throws Exception;
}
