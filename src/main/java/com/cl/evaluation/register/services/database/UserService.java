package com.cl.evaluation.register.services.database;

import com.cl.evaluation.register.entities.UserEntity;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.repositories.UserRepository;
import com.cl.evaluation.register.services.TimeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService extends AbmService<UserRepository, UserEntity> {
    public static String USER_NOT_FOUND = "Usuario no registrado";
    private final TimeService timeService;
    @Autowired
    public UserService(TimeService timeService) {
        this.timeService = timeService;
    }
    public void updateToken(UserEntity user, String token) throws EntityNotFoundException {
        user.setToken(token);
        user.setLastLogin(timeService.getCurrentTime());
        repository.save(user);
    }

    public UserEntity findByEmail(String email) {
        UserEntity user = repository.findFirstByEmail(email);
        if (user == null)
            throw new EntityNotFoundException(USER_NOT_FOUND);
        return user;
    }
}
