package com.practice.examportal.registration.role;

import com.practice.examportal.registration.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserRole {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long Id;

@ManyToOne(fetch = FetchType.EAGER)
private UserEntity userEntity;

@ManyToOne(fetch = FetchType.EAGER)
private RoleEntity roleEntity;
    
}
