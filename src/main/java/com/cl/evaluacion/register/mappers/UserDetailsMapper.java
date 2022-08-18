package com.cl.evaluacion.register.mappers;

import com.cl.evaluacion.register.entities.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsMapper {
    public UserDetails toUserDetails(UserEntity userEntity) {
        return new User(userEntity.getEmail(), userEntity.getToken(), new ArrayList<>());
    }
}
