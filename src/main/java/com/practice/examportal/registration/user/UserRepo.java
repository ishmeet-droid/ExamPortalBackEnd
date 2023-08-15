package com.practice.examportal.registration.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    
    public UserEntity findByUsername(String userName);
}
