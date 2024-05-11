package com.practice.examportal.registration.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.examportal.authentication.Authority;
import com.practice.examportal.registration.role.UserRole;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserEntity implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;
    private String fName;
    private String lName;
    private String password;
    private String email;
    private String phone;
    private String profile;
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userEntity")
    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Defining role of person...
        Set<Authority> set = new HashSet<>();

        //adding authority usingroles.roles
        this.userRoles.forEach(userRole ->{
            set.add(
                new Authority(userRole.getRoleEntity().getRoleName())
            );
        });


        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
       
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       
        return true;
    }

  
    // public Set<UserRole> getUserRoles() {
    //     return userRoles;
    // }

    



}
