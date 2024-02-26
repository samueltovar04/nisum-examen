package com.nisum.examen;

import com.nisum.examen.model.dto.PhoneDto;
import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.request.PhoneRequest;
import com.nisum.examen.model.request.UserRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Factory {
  public static UserRequest getUserRequest(List<PhoneRequest> phonesRequest) {
    return UserRequest.builder()
        .name("sam")
        .email("stovar@gmail.com")
        .password("Clave1234")
        .phones(phonesRequest)
        .build();
  }
  public static UserRequest getUserRequestEmail(List<PhoneRequest> phonesRequest) {
    return UserRequest.builder()
        .name("sam")
        .email("email")
        .password("Clave1234")
        .phones(phonesRequest)
        .build();
  }

  public static UserDto getUserDto(List<PhoneDto> phones){
    return UserDto.builder()
        .id(UUID.randomUUID())
        .name("sam")
        .email("stovar@gmail.com")
        .created(LocalDateTime.now())
        .modified(LocalDateTime.now())
        .lastLogin(LocalDateTime.now())
        .token("token")
        .isActive(true)
        .phones(phones)
        .build();
  }

  public static PhoneDto getPhoneDto() {
    return PhoneDto.builder()
        .number(1L)
        .cityCode(1L)
        .countryCode(1L)
        .build();
  }
  public static PhoneRequest getPhoneRequest(){
    return PhoneRequest.builder()
        .number(1L)
        .cityCode(1L)
        .countryCode(1L)
        .build();
  }
}
