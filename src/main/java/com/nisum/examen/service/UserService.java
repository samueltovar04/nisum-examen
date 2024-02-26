package com.nisum.examen.service;

import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.request.UserRequest;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto saveUser(UserRequest user);

    UserDto getUser(UUID id);

    List<UserDto> getAllUser();

    UserDto updateUser(UUID id, UserRequest user);

    boolean deleteUser(UUID id);
}
