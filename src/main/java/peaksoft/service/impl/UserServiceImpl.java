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
import peaksoft.dto.user.UserResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtService;

    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("Email already exists");
        }

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(ZonedDateTime.from(request.getDateOfBirth()))
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(Role.CHEF)
                .experience(request.getExperience())
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

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findByIdUser(id).orElseThrow(()->new NotFoundException("User with id: " + id + "is not fount"));
    }

    @Override
    public List<UserResponse> getAllUser() {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        if(id!=1L) {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new NotFoundException(String.format("Author with email :%s already exists", id)));
            userRepository.deleteById(id);
            return user.getEmail() + " is deleted!";
        }else {
            throw new BadRequestException("user by id 1L dont deleted");
        }
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return null;
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
