package com.Aman.SpringSecurityProject.dto;

import com.Aman.SpringSecurityProject.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
}
