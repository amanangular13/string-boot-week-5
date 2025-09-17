package com.Aman.SpringSecurityProject.controllers;

import com.Aman.SpringSecurityProject.dto.LoginDTO;
import com.Aman.SpringSecurityProject.dto.SignUpDTO;
import com.Aman.SpringSecurityProject.dto.UserDTO;
import com.Aman.SpringSecurityProject.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = authService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        String token = authService.login(loginDTO);
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
