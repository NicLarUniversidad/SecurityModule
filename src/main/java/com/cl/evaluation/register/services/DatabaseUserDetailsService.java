/*
package com.cl.evaluation.register.services;

import com.cl.evaluation.register.mappers.UserDetailsMapper;
import com.cl.evaluation.register.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    @Autowired
    public DatabaseUserDetailsService(UserRepository userRepository,
                                      UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String mail)
            throws UsernameNotFoundException {
        var user =
                userRepository.findFirstByEmail(mail);
        return userDetailsMapper.toUserDetails(user);
    }
}
*/
