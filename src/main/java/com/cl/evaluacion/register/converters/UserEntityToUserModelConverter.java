package com.cl.evaluacion.register.converters;

import com.cl.evaluacion.register.entities.UserEntity;
import com.cl.evaluacion.register.models.UserModel;
import org.springframework.stereotype.Service;

@Service
public class UserEntityToUserModelConverter {
    public UserModel convert(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setCreated(userEntity.getCreated());
        userModel.setActive(userEntity.isActive());
        userModel.setEmail(userEntity.getEmail());
        userModel.setToken(userEntity.getToken());
        userModel.setModified(userEntity.getModified());
        userModel.setLastLogin(userEntity.getLastLogin());
        userModel.setPhones(userEntity.getPhones());
        return userModel;
    }
}
