package com.example.shoponlineapi.controller;

import com.example.shoponlineapi.dto.request.SigUpForm;
import com.example.shoponlineapi.dto.response.JwtResponse;
import com.example.shoponlineapi.dto.response.ResponseMessage;
import com.example.shoponlineapi.model.Role;
import com.example.shoponlineapi.model.RoleName;
import com.example.shoponlineapi.model.User;
import com.example.shoponlineapi.security.jwt.JwtProvider;
import com.example.shoponlineapi.security.userprincal.UserPrinciple;
import com.example.shoponlineapi.service.impl.RoleService;
import com.example.shoponlineapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/authenticate")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SigUpForm sigUpForm) {
        if (userService.existsByEmail(sigUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("the username existed! please try again!"), HttpStatus.OK);
        }
        if (userService.existsByEmail(sigUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("the email existed! please try again!"), HttpStatus.OK);
        }
        User user = new User(sigUpForm.getName(), sigUpForm.getUsername(), sigUpForm.getEmail(), passwordEncoder.encode(sigUpForm.getPassword()));
        Set<String> strRole = sigUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRole.forEach(role -> {
            switch (role) {
                case "admin" :
                    Role adminRole = roleService.findAllByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("role not found"));
                    roles.add(adminRole);
                    break;
                case "customer" :
                    Role customerRole = roleService.findAllByName(RoleName.CUSTOMER).orElseThrow(
                            ()-> new RuntimeException("role not found"));
                    roles.add(customerRole);
                    break;
                case "employee" :
                    Role employeeRole = roleService.findAllByName(RoleName.EMPLOYEE).orElseThrow(
                            ()-> new RuntimeException("role not found"));
                    roles.add(employeeRole);
                    break;
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("create user success"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SigUpForm sigUpForm) {
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(sigUpForm.getUsername(), sigUpForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
}
