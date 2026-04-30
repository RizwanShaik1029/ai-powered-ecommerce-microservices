package com.srb.auth.service;

import com.srb.auth.model.User;
import com.srb.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository= userRepository;
    }

    public String addUserToDB(User user) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
        user.setPassword(bcrypt.encode(user.getPassword()));
        User u = userRepository.save(user);
        if(u!=null)
        {
            return "success";
        }
        return "failure";
    }

    public User getUserDetails(String phNumber) {

        return userRepository.findByPhNumber(phNumber);

    }
}
