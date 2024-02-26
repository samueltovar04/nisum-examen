package com.nisum.examen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.examen.Factory;
import com.nisum.examen.model.dto.PhoneDto;
import com.nisum.examen.model.request.PhoneRequest;
import com.nisum.examen.model.request.UserRequest;
import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.service.UserService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void whenRegisterUserSuccess() throws Exception {
      PhoneDto phone = Factory.getPhoneDto();
      List<PhoneDto> phones = Collections.singletonList(phone);
      UserDto mockResponse = Factory.getUserDto(phones);

      PhoneRequest phoneRequest = Factory.getPhoneRequest();
      List<PhoneRequest> phonesRequest =  Collections.singletonList(phoneRequest);
      UserRequest requestDTO = Factory.getUserRequest(phonesRequest);

      when(userService.saveUser(any(UserRequest.class))).thenReturn(mockResponse);

      mockMvc.perform(post("/api/v1/users")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(requestDTO)))
          .andExpect(MockMvcResultMatchers.status().isCreated())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andReturn();

      verify(userService, times(1)).saveUser(any(UserRequest.class));
      verifyNoMoreInteractions(userService);
    }

  @Test
  void whenGetUserSuccess() throws Exception {
    PhoneDto phone = Factory.getPhoneDto();
    List<PhoneDto> phones = Collections.singletonList(phone);
    UserDto mockResponse = Factory.getUserDto(phones);

    when(userService.getUser(any())).thenReturn(mockResponse);

    mockMvc.perform(get("/api/v1/users/B550e8400-e29b-41d4-a716-4466")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

    verify(userService, times(1)).getUser(any());
    verifyNoMoreInteractions(userService);
  }

  @Test
  void whenGetUserInternalError() throws Exception {

    mockMvc.perform(get("/api/v1/users/dfsdfsfdsd")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isInternalServerError())
        .andReturn();

    verify(userService, never()).getUser(any());
  }

  @Test
  void whenGetUserAllSuccess() throws Exception {
    PhoneDto phone = Factory.getPhoneDto();
    List<PhoneDto> phones = Collections.singletonList(phone);
    List<UserDto> mockResponse = Collections.singletonList(Factory.getUserDto(phones));

    when(userService.getAllUser()).thenReturn(mockResponse);

    mockMvc.perform(get("/api/v1/users/all")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

    verify(userService, times(1)).getAllUser();
    verifyNoMoreInteractions(userService);
  }

  @Test
  void whenUpdateUserSuccess() throws Exception {
    PhoneDto phone = Factory.getPhoneDto();
    List<PhoneDto> phones = Collections.singletonList(phone);
    UserDto mockResponse = Factory.getUserDto(phones);

    PhoneRequest phoneRequest = Factory.getPhoneRequest();
    List<PhoneRequest> phonesRequest =  Collections.singletonList(phoneRequest);
    UserRequest requestDTO = Factory.getUserRequest(phonesRequest);

    when(userService.updateUser(any(),any(UserRequest.class))).thenReturn(mockResponse);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/" + mockResponse.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    verify(userService, times(1)).updateUser(any(),any(UserRequest.class));
    verifyNoMoreInteractions(userService);
  }

  @Test
  void whenUpdateUserNotFound() throws Exception {
    PhoneRequest phoneRequest = Factory.getPhoneRequest();
    List<PhoneRequest> phonesRequest =  Collections.singletonList(phoneRequest);
    UserRequest requestDTO = Factory.getUserRequest(phonesRequest);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn();

    verify(userService, never()).updateUser(any(),any(UserRequest.class));
  }
}
