package com.fundoo.userservice.service;

import com.fundoo.userservice.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserByEmail(String email);

}