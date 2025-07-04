package com.tz.service;

import com.tz.config.JwtProvider;
import com.tz.model.User;
import com.tz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = findUserByEmail(email);

        if(user == null) {
            throw new Exception("User not found with JWT token: " + jwt);
        }

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("User not found with email: " + email);
        }

        return user;
    }
}
