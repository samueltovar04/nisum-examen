package com.nisum.examen.service;

import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.request.UserRequest;

public interface UserService {
    UserDto saveUser(UserRequest user);
}
