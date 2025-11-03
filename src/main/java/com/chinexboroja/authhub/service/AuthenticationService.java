package com.chinexboroja.authhub.service;

import com.chinexboroja.authhub.dtos.LoginUserDto;
import com.chinexboroja.authhub.dtos.RegisterUserDto;
import com.chinexboroja.authhub.enums.RoleEnum;
import com.chinexboroja.authhub.model.Role;
import com.chinexboroja.authhub.model.User;
import com.chinexboroja.authhub.repository.RoleRepository;
import com.chinexboroja.authhub.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    public User signUp(RegisterUserDto registerUserDto) {

        Optional<Role> roleOptional = roleRepository.findByName(RoleEnum.USER);
        if (roleOptional.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setFullName(registerUserDto.fullName());
        user.setEmail(registerUserDto.email());
        user.setPassword(passwordEncoder.encode(registerUserDto.password()));
        user.setRole(roleOptional.get());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        return userRepository.save(user);
    }

    public User login(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDto.email(), loginUserDto.password()));

        return userRepository.findByEmail(loginUserDto.email()).orElseThrow();
    }
}
