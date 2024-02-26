package com.nisum.examen.service.impl;

import com.nisum.examen.exception.EmailExistException;
import com.nisum.examen.exception.InvalidJodException;
import com.nisum.examen.exception.UserExistException;
import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.mapper.UserMapper;
import com.nisum.examen.model.request.UserRequest;
import com.nisum.examen.repository.UserRepository;
import com.nisum.examen.service.UserService;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(final UserRepository repository,final UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto saveUser(UserRequest user) {
        validatedEmail(user.getEmail());

        return userMapper.convert(repository.saveAndFlush(userMapper.convert(user)));
    }

    @Override
    public UserDto getUser(UUID id) {
        return userMapper.convert(
            repository.findById(id).orElseThrow(
                ()-> new UserExistException("User not exists id " + id)));
    }

    @Override
    public List<UserDto> getAllUser() {
        return repository.findAll()
            .stream().map(userMapper::convert)
            .toList();
    }

    @Override
    public UserDto updateUser(UUID id, UserRequest user) {
        if (!repository.existsById(id))
            throw new UserExistException("User not exists id " + id);
        return userMapper.convert(repository
            .saveAndFlush(userMapper.convert(user)));
    }

    @Override
    public boolean deleteUser(UUID id) {
        repository.deleteById(id);
        return repository.existsById(id);
    }

    public void validatedEmail(String jod) throws InvalidJodException {
        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        Matcher matcher = pattern.matcher(jod);

        if (!matcher.find()) {
            throw new InvalidJodException("Email Format Invalid");
        }
        if(repository.existsByEmail(jod)){
            throw new EmailExistException("Email exists : "+ jod);
        }
    }
}
