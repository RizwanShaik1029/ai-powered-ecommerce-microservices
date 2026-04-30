package com.srb.auth.service;

import com.srb.auth.model.User;
import com.srb.auth.model.UserPrinciple;
import com.srb.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetails(UserRepository userRepo)
    {
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String PhNumber) throws UsernameNotFoundException {
        User user = userRepo.findByPhNumber(PhNumber);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(user);
    }
}
