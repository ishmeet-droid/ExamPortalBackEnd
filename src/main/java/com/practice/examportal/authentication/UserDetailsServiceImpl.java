package com.practice.examportal.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.examportal.registration.user.UserEntity;
import com.practice.examportal.registration.user.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private UserRepo userRepo;

    

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity user = userRepo.findByUsername(username);

        if(user == null){
            System.out.println("User Not found");
            throw new UsernameNotFoundException("InValid Credentials");
        }
        return user;
    }
    
}
