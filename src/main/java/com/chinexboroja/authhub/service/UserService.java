package com.chinexboroja.authhub.service;

import com.chinexboroja.authhub.dtos.RegisterUserDto;
import com.chinexboroja.authhub.enums.RoleEnum;
import com.chinexboroja.authhub.model.Role;
import com.chinexboroja.authhub.model.User;
import com.chinexboroja.authhub.repository.RoleRepository;
import com.chinexboroja.authhub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createAdministrator(RegisterUserDto registerUserDto) {
        Optional<Role> roleOptional = roleRepository.findByName(RoleEnum.ADMIN);

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
}
