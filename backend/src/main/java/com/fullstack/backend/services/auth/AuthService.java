package com.fullstack.backend.services.auth;


import com.fullstack.backend.dto.SignupDTO;
import com.fullstack.backend.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
