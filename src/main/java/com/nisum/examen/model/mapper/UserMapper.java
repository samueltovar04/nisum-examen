package com.nisum.examen.model.mapper;

import com.nisum.examen.model.dto.PhoneDto;
import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.entity.Phone;
import com.nisum.examen.model.entity.User;
import com.nisum.examen.model.request.PhoneRequest;
import com.nisum.examen.model.request.UserRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class UserMapper {
    public User convert(UserRequest source){
       return User.builder()
                .id(UUID.randomUUID())
                .name(source.getName())
               .email(source.getEmail())
               .password(source.getPassword())
               .phones(covert(source.getPhones()))
               .token(getToken(source))
               .created(LocalDateTime.now())
               .lastLogin(LocalDateTime.now())
               .isActive(Boolean.TRUE)
               .build();
    }

    public UserDto convert(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phones(covertDto(user.getPhones()))
                .token(user.getToken())
                .created(user.getCreated())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .build();
    }

    List<Phone> covert(List<PhoneRequest> phone) {
        return phone.stream().map(this::mapperPhone).toList();
    }

    Phone mapperPhone(PhoneRequest p){
        return Phone.builder()
                .number(p.getNumber())
                .cityCode(p.getCityCode())
                .countryCode(p.getCountryCode())
                .build();
    }

    List<PhoneDto> covertDto(List<Phone> phone) {
        return phone.stream().map(this::mapperPhoneDto).toList();
    }

    PhoneDto mapperPhoneDto(Phone p){
        return PhoneDto.builder()
                .number(p.getNumber())
                .cityCode(p.getCityCode())
                .countryCode(p.getCountryCode())
                .build();
    }

    private String getToken(UserRequest user){
        return Jwts.builder()
                .setId(String.valueOf(System.currentTimeMillis()))
                .setSubject(user.getName())
                .claim("userName", user.getName())
                .claim("userEmail", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, "HSGSFSF")
                .compact();
    }
}
