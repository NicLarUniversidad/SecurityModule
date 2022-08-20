package com.cl.evaluation.register.services;

import com.cl.evaluation.register.converters.UserEntityToUserModelConverter;
import com.cl.evaluation.register.entities.UserEntity;
import com.cl.evaluation.register.expections.InvalidFormatException;
import com.cl.evaluation.register.expections.UserAlreadyExistsException;
import com.cl.evaluation.register.models.RegisterUserModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.repositories.PhoneRepository;
import com.cl.evaluation.register.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final ValidatorService validatorService;
    private final TimeService timeService;
    private final UserEntityToUserModelConverter userEntityToUserModelConverter;
    private final PasswordEncoder passwordEncoder;
    private final PhoneRepository phoneRepository;
    @Autowired
    public RegisterService(UserRepository userRepository,
                           ValidatorService validatorService,
                           TimeService timeService,
                           UserEntityToUserModelConverter userEntityToUserModelConverter,
                           PasswordEncoder passwordEncoder,
                           PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.validatorService = validatorService;
        this.timeService = timeService;
        this.userEntityToUserModelConverter = userEntityToUserModelConverter;
        this.passwordEncoder = passwordEncoder;
        this.phoneRepository = phoneRepository;
    }
    public UserModel registerUser(RegisterUserModel registerUserModel) throws InvalidFormatException, UserAlreadyExistsException {
        this.validatorService.validateEmail(registerUserModel.getEmail());
        this.validatorService.validatePassword(registerUserModel.getPassword());
        UserEntity user = this.userRepository.findFirstByEmail(registerUserModel.getEmail());
        if (user==null) {
            user = createNewUser(registerUserModel);
            if (registerUserModel.getPhones() != null)
                phoneRepository.saveAll(registerUserModel.getPhones());
            this.userRepository.save(user);
        } else {
            String msg = String.format("Ya hay un usuario registrado con el mail %s", registerUserModel.getEmail());
            throw new UserAlreadyExistsException(msg);
        }
        return userEntityToUserModelConverter.convert(user);
    }

    private UserEntity createNewUser(RegisterUserModel registerUserModel) {
        UserEntity newUser = new UserEntity();
        newUser.setPassword(passwordEncoder.encode(registerUserModel.getPassword()));
        newUser.setCreated(timeService.getCurrentTime());
        newUser.setModified(timeService.getCurrentTime());
        newUser.setEmail(registerUserModel.getEmail());
        newUser.setToken(generateUuid());
        newUser.setActive(true);
        newUser.setPhones(registerUserModel.getPhones());
        return newUser;
    }

    private String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public Iterable<UserEntity> getAllUser() {
        return this.userRepository.findAll();
    }
}
