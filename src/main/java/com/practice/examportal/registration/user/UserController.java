package com.practice.examportal.registration.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.examportal.registration.role.RoleEntity;
import com.practice.examportal.registration.role.UserRole;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // create user... by API call...
    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity userCreated = null;
        try {

            RoleEntity role1 = new RoleEntity();
            role1.setRoleName("ADMIN");
            RoleEntity role2 = new RoleEntity();
            role2.setRoleName("NORMAL");

            Set<UserRole> userRoleSet = new HashSet<>();

            UserRole ur1 = new UserRole();
            ur1.setRoleEntity(role1);
            ur1.setUserEntity(user);

            UserRole ur2 = new UserRole();
            ur2.setRoleEntity(role2);
            ur2.setUserEntity(user);

            userRoleSet.add(ur1);
            userRoleSet.add(ur2);

            userCreated = userService.createUser(user, userRoleSet);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(userCreated,HttpStatus.CREATED);
    }

    // TODO add get method, delete method by the help of username... in service and
    // controller...

    @GetMapping("/show-users/{username}")
    public ResponseEntity<UserEntity> getUserEntity(@PathVariable("username") String username) {

        UserEntity user = this.userService.getUserByUserName(username);
        if (user != null)
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);

        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username){
        
        this.userService.deleteUser(username);
        

         return new ResponseEntity<>(HttpStatus.ACCEPTED);
        // if (user != null)

        //     return new ResponseEntity<>(HttpStatus.ACCEPTED);

        // else
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
