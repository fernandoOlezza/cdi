package com.exercice.cdi.service.impl;

import com.exercice.cdi.controller.dto.LoginResponseDTO;
import com.exercice.cdi.controller.dto.SignUpRequestDTO;
import com.exercice.cdi.controller.dto.SignUpRespDTO;
import com.exercice.cdi.exception.EmailDuplicadoException;
import com.exercice.cdi.exception.UserNotFoundException;
import com.exercice.cdi.repository.UserRepository;
import com.exercice.cdi.repository.entity.UserEntity;
import com.exercice.cdi.service.LoginService;
import com.exercice.cdi.util.AESUtil;
import com.exercice.cdi.util.JwtUtil;
import com.exercice.cdi.util.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public SignUpRespDTO registrarUsuario(SignUpRequestDTO request) throws Exception {

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new EmailDuplicadoException("El email " + request.getEmail() + " ya existe");
    }

    UserEntity user = new UserEntity();
    user.setName(request.getName() == null? "": request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(AESUtil.encrypt(request.getPassword()));
    user.setCreated(LocalDateTime.now());
    user.setToken(jwtUtil.generateToken(user.getEmail()));
    user.setIsActive(true);

    user.setPhones(PhoneMapper.toEntityList(request.getPhones(), user));

    userRepository.save(user);

    SignUpRespDTO response = new SignUpRespDTO();
    response.setId(user.getId());
    response.setCreated(user.getCreated());
    response.setLastLogin(user.getLastLogin());
    response.setToken(user.getToken());
    response.setIsActive(user.getIsActive());

    return response;
  }

  @Override
  public LoginResponseDTO realizarLogin(String token) throws Exception {
    String email = jwtUtil.extractEmail(token);
    Optional<UserEntity> user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new UserNotFoundException("El email " + email + " no existe");
    }

    UserEntity userEntity = user.get();
    userEntity.setLastLogin(LocalDateTime.now());
    userRepository.save(userEntity);

    LoginResponseDTO response = new LoginResponseDTO();
    response.setId(userEntity.getId());
    response.setCreated(userEntity.getCreated());
    response.setLastLogin(userEntity.getLastLogin());
    response.setToken(userEntity.getToken());
    response.setIsActive(userEntity.getIsActive());
    response.setName(userEntity.getName());
    response.setEnamil(email);
    response.setPassword(AESUtil.decrypt(userEntity.getPassword()));
    response.setPhones(PhoneMapper.toDTOList(userEntity.getPhones()));

    return response;
  }

}
