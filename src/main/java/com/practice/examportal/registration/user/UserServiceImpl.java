package com.practice.examportal.registration.user;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.practice.examportal.registration.role.RoleRepo;
import com.practice.examportal.registration.role.UserRole;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private RoleRepo roleRepo;

    UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo){

        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    //creating user
    @Override
    public UserEntity createUser(UserEntity user, Set<UserRole> userRoles) throws Exception {
        
       UserEntity local = this.userRepo.findByUsername(user.getUsername());
       
       if(local != null){
            System.out.println("user already there");
            throw new Exception("user aleardy present!!!");
       }
       else{
        
            for(UserRole userRole : userRoles){
                roleRepo.save(userRole.getRoleEntity());
            }

            user.setUserRoles(userRoles);
            local =  userRepo.save(user);
       }
       return local;
    }
    
}
