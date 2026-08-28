package com.proyectocoryb.service;

import com.proyectocoryb.dto.request.AuthLoginRequestDTO;
import com.proyectocoryb.dto.request.AuthRegisterRequestDTO;
import com.proyectocoryb.dto.response.AuthResponseDTO;
import com.proyectocoryb.dto.response.MessageResponseDTO;

public interface AuthService {

    MessageResponseDTO registrar(AuthRegisterRequestDTO request);

    AuthResponseDTO login(AuthLoginRequestDTO request);
}
