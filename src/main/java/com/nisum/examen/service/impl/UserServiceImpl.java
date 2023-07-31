package com.nisum.examen.service.impl;

import com.nisum.examen.exception.EmailExistException;
import com.nisum.examen.exception.InvalidJodException;
import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.entity.User;
import com.nisum.examen.model.mapper.UserMapper;
import com.nisum.examen.model.request.UserRequest;
import com.nisum.examen.repository.UserRepository;
import com.nisum.examen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(final UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto saveUser(UserRequest user) {
        validatedEmail(user.getEmail());

        return userMapper.convert(repository.saveAndFlush(userMapper.convert(user)));
    }

    public void validatedEmail(String jod) throws InvalidJodException {
        Pattern pattern = Pattern.compile("^[\\\\w+]+(\\\\.[\\\\w-]{1,62}){0,126}@[\\\\w-]{1,63}(\\\\.[\\\\w-]{1,62})+/[\\\\w-]+$");
        Matcher matcher = pattern.matcher(jod);

        if (!matcher.find()) {
            throw new InvalidJodException("Email Format Invalid");
        }
        if(repository.findUserByEmailExists(jod)){
            throw new EmailExistException("Email exists : "+ jod);
        }
    }
}
