package com.nisum.examen.service;

import com.nisum.examen.Factory;
import com.nisum.examen.model.dto.PhoneDto;
import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.entity.User;
import com.nisum.examen.model.mapper.UserMapper;
import com.nisum.examen.model.request.PhoneRequest;
import com.nisum.examen.model.request.UserRequest;
import com.nisum.examen.repository.UserRepository;
import com.nisum.examen.service.impl.UserServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userDTOMapper;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void whenRegisterUserSuccess() {
    PhoneDto phone = Factory.getPhoneDto();
    List<PhoneDto> phones = Collections.singletonList(phone);
    UserDto expectedResponse = Factory.getUserDto(phones);

    PhoneRequest phoneRequest = Factory.getPhoneRequest();
    List<PhoneRequest> phonesRequest =  Collections.singletonList(phoneRequest);
    UserRequest userRequest = Factory.getUserRequest(phonesRequest);

    User user = new User();

    when(userRepository.saveAndFlush(any())).thenReturn(user);
    when(userDTOMapper.convert(any(UserRequest.class))).thenReturn(user);
    when(userDTOMapper.convert(any(User.class))).thenReturn(expectedResponse);

    UserDto actualResponse = userService.saveUser(userRequest);

    assertEquals(expectedResponse, actualResponse);
    verify(userRepository, times(1)).saveAndFlush(any(User.class));
    verify(userDTOMapper, times(1)).convert(user);
  }

  @Test
  void whenRegisterUserWithEmailAlreadyRegistered() {
    PhoneRequest phoneRequest = Factory.getPhoneRequest();
    List<PhoneRequest> phonesRequest =  Collections.singletonList(phoneRequest);
    UserRequest userRequest = Factory.getUserRequestEmail(phonesRequest);

    assertThrows(Exception.class, () -> userService.saveUser(userRequest));

    verify(userRepository, never()).save(any(User.class));
    verify(userDTOMapper, never()).convert(any(User.class));
  }
}
