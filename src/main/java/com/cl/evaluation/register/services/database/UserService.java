package com.cl.evaluation.register.services.database;

import com.cl.evaluation.register.entities.UserEntity;
import com.cl.evaluation.register.repositories.UserRepository;
import com.cl.evaluation.register.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService extends AbmService<UserRepository, UserEntity> {
    private final TimeService timeService;
    @Autowired
    public UserService(TimeService timeService) {
        this.timeService = timeService;
    }
    public void updateToken(UserEntity user, String token) throws EntityNotFoundException {
        user.setToken(token);
        user.setLastLogin(timeService.getCurrentTimestamp());
        repository.save(user);
    }

    public UserEntity findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }
}
