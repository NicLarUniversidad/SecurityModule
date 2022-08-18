package com.cl.evaluacion.register.services;

import com.cl.evaluacion.register.converters.UserEntityToUserModelConverter;
import com.cl.evaluacion.register.entities.UserEntity;
import com.cl.evaluacion.register.expections.InvalidFormatException;
import com.cl.evaluacion.register.expections.UserAlreadyExistsException;
import com.cl.evaluacion.register.models.RegisterUserModel;
import com.cl.evaluacion.register.models.UserModel;
import com.cl.evaluacion.register.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final ValidatorService validatorService;
    private final TimeService timeService;
    private final UserEntityToUserModelConverter userEntityToUserModelConverter;
    @Autowired
    public RegisterService(UserRepository userRepository,
                           ValidatorService validatorService,
                           TimeService timeService,
                           UserEntityToUserModelConverter userEntityToUserModelConverter) {
        this.userRepository = userRepository;
        this.validatorService = validatorService;
        this.timeService = timeService;
        this.userEntityToUserModelConverter = userEntityToUserModelConverter;
    }
    public UserModel registerUser(RegisterUserModel registerUserModel) throws InvalidFormatException, UserAlreadyExistsException {
        this.validatorService.validateEmail(registerUserModel.getEmail());
        this.validatorService.validatePassword(registerUserModel.getPassword());
        UserEntity user = this.userRepository.findFirstByEmail(registerUserModel.getEmail());
        if (user==null) {
            user = createNewUser(registerUserModel);
            this.userRepository.save(user);
        } else {
            String msg = String.format("Ya hay un usuario registrado con el mail %s", registerUserModel.getEmail());
            throw new UserAlreadyExistsException(msg);
        }
        return userEntityToUserModelConverter.convert(user);
    }

    private UserEntity createNewUser(RegisterUserModel registerUserModel) {
        UserEntity newUser = new UserEntity();
        newUser.setId(generateUuid());
        newUser.setCreated(timeService.getCurrentTime());
        newUser.setModified(timeService.getCurrentTime());
        newUser.setEmail(registerUserModel.getEmail());
        newUser.setToken(generateUuid());
        newUser.setActive(true);
        return newUser;
    }

    private String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
