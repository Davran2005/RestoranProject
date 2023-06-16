package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
import java.time.ZonedDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtService;
    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("Email already exists");
        }
        User user = new User();

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(ZonedDateTime.from(request.getDateOfBirth()))
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.phoneNumber())
                .role(Role.ADMIN)
                .experience(request.experience())
                .build();

        userRepository.save(user);

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User with email: " + request.getEmail() + " not found!")
        );

        if (request.getPassword().isBlank()) {
            throw new BadCredentialsException("Password is blank");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
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
        UserRequest user = new UserRequest(
                "davran",
                "joldoshbaev",
                LocalDate.now(),
                "davran@gmail.com",
                "davran",
                "+996777666555",
                Role.ADMIN,
                2
        );
        }
    }

