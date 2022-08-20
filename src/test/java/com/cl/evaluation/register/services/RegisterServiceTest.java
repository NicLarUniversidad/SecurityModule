package com.cl.evaluation.register.services;

import com.cl.evaluation.register.ApplicationTest;
import com.cl.evaluation.register.entities.PhoneEntity;
import com.cl.evaluation.register.entities.UserEntity;
import com.cl.evaluation.register.expections.InvalidFormatException;
import com.cl.evaluation.register.expections.UserAlreadyExistsException;
import com.cl.evaluation.register.models.RegisterUserModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterServiceTest extends ApplicationTest {
    private final RegisterService registerService;
    private static UserEntity userWithoutPhones;
    private static UserEntity userWithPhones;
    private static RegisterUserModel registerUserModel;
    @Autowired
    public RegisterServiceTest(RegisterService registerService) {
        this.registerService = registerService;
    }
    @BeforeAll
    public static void beforeAll() {
        registerUserModel = new RegisterUserModel();
        registerUserModel.setEmail("nicolarena@hotmail.com");
        registerUserModel.setPassword("supersecreta123@");
        registerUserModel.setName("Nicolas");
        var phones = new ArrayList<PhoneEntity>();
        phones.add(PhoneEntity
                        .builder()
                        .number(556453)
                        .cityCode(2324)
                        .countryCode(54)
                        .build()
        );
        phones.add(PhoneEntity
                .builder()
                .number(559891)
                .cityCode(2324)
                .countryCode(54)
                .build()
        );
        registerUserModel.setPhones(phones);

        userWithPhones = new UserEntity();
        userWithPhones.setEmail("nic.lar.universidad@gmail.com");
        userWithPhones.setName(registerUserModel.getName());
        userWithPhones.setPhones(registerUserModel.getPhones());

        userWithoutPhones = new UserEntity();
        userWithoutPhones.setEmail(registerUserModel.getEmail());
        userWithoutPhones.setName(registerUserModel.getName());
        userWithoutPhones.setPhones(null);
    }

    @Test
    void registerUserWithoutPhonesTest() throws UserAlreadyExistsException, InvalidFormatException {
        registerUserModel.setPhones(userWithoutPhones.getPhones());
        registerUserModel.setEmail(userWithoutPhones.getEmail());
        var newUser = this.registerService.registerUser(registerUserModel);
        assertThat(newUser.getEmail(), equalTo(userWithoutPhones.getEmail()));
    }

    @Test
    void registerUserWithPhonesTest() throws UserAlreadyExistsException, InvalidFormatException {
        registerUserModel.setPhones(userWithPhones.getPhones());
        registerUserModel.setEmail(userWithPhones.getEmail());
        var newUser = this.registerService.registerUser(registerUserModel);
        assertThat(newUser.getEmail(), equalTo(userWithPhones.getEmail()));
        assertThat(newUser.getPhones().get(1).getNumber(), equalTo(userWithPhones.getPhones().get(1).getNumber()));
    }
}
