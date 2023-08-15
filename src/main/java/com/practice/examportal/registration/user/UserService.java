package com.practice.examportal.registration.user;

import java.util.Set;

import com.practice.examportal.registration.role.UserRole;


public interface UserService {
    
    //creating user
    public UserEntity createUser(UserEntity user, Set<UserRole> userRoles) throws Exception;
}
