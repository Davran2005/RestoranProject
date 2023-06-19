package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtServices;
import peaksoft.dto.Authentication.AuthenticationResponse;
import peaksoft.dto.Authentication.SignInRequest;
import peaksoft.dto.Authentication.SignUpRequest;
import peaksoft.dto.user.UserRequest;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Scanner;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtServices jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EntityExistsException("Email already exists");
        }
        Period between = Period.between(signUpRequest.getDateOfBirth(), LocalDate.now());
        int age = between.getYears();
        if (signUpRequest.getRole().equals(Role.CHEF)) {
            if (age >= 25 && age <= 45) {
                user.setDateOfBirth(ZonedDateTime.from(signUpRequest.getDateOfBirth()));
            } else {
                throw new BadCredentialsException("Возраст должен быть не меньше 25 и не больше 45");
            }
        }
        if (signUpRequest.getRole().equals(Role.WAITER)) {
            if (age >= 18 && age <= 30) {
                user.setDateOfBirth(ZonedDateTime.from(signUpRequest.getDateOfBirth()));
            } else {
                throw new BadCredentialsException("Возраст должен быть не меньше 18 и не больше 30");
            }
        }
        if (signUpRequest.getRole().equals(Role.CHEF)) {
            if (signUpRequest.getExperience() < 2) {
                user.setExperience(signUpRequest.getExperience());
            } else {
                throw new BadCredentialsException("У повара должен быть стаж больше 2 года");
            }
        }
        if (signUpRequest.getRole().equals(Role.WAITER)) {
            if (signUpRequest.getExperience() < 1) {
                user.setExperience(signUpRequest.getExperience());
            } else {
                throw new BadCredentialsException("У повара должен быть стаж больше 1 года");
            }
        }
        String password = new Scanner(System.in).nextLine();
        int a = Integer.parseInt(password);
        if (a > 4) {
            user.setPassword(signUpRequest.getPassword());
        }else {
            throw new BadCredentialsException("пароль должен быть больше 4");
        }
        userRepository.save(user);

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User with email: " + signInRequest.getEmail() + " not found!")
        );

        if (signInRequest.getPassword().isBlank()) {
            throw new BadCredentialsException("Password is blank");
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }


    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Davran");
        user.setLastName("Joldoshbaev");
        user.setDateOfBirth(ZonedDateTime.now());
        user.setEmail("d@gmail.com");
        user.setPassword(passwordEncoder.encode("davran00"));
        user.setPhoneNumber("+996995665528");
        user.setRole(Role.ADMIN);
        user.setExperience(3);
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }

    }
}
