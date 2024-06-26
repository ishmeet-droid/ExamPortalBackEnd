package com.practice.examportal;

// import java.util.HashSet;
// import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.practice.examportal.registration.role.RoleEntity;
import com.practice.examportal.registration.role.RoleRepo;
// import com.practice.examportal.registration.role.UserRole;
// import com.practice.examportal.registration.user.UserEntity;
// import com.practice.examportal.registration.user.UserService;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner{

	RoleRepo roleRepo;

	ExamportalApplication(RoleRepo roleRepo){
		this.roleRepo = roleRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Testing User Service");
		

		//creating user by Command Line
		// UserEntity us = new UserEntity();

		// us.setFName("ish");
		// us.setLName("kl");
		// us.setEmail("@gmail");
		// us.setPassword("********");
		// us.setPhone("908979");
		// us.setProfile("image//");
		// us.setUsername("noob");

		RoleEntity role1 = new RoleEntity();
		role1.setRoleName("ADMIN");

		RoleEntity role2 = new RoleEntity();
		role2.setRoleName("NORMAL");

		
        roleRepo.save(role1);
		roleRepo.save(role2);

		// for(UserRole userRole : userRoles){
        //     //     roleRepo.save(userRole.getRoleEntity());

		// Set<UserRole> userRoleSet = new HashSet<>();

		// UserRole ur1 = new UserRole();
		// ur1.setRoleEntity(role1);
		// ur1.setUserEntity(us);

		// UserRole ur2 = new UserRole();
		// ur2.setRoleEntity(role2);
		// ur2.setUserEntity(us);

		// userRoleSet.add(ur1);
		// userRoleSet.add(ur2);


		// UserEntity user1 = userService.createUser(us, userRoleSet);

		// System.out.println(user1.getUsername());

		
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
