package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtServices;

import peaksoft.dto.SimpleResponse;
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
    public UserResponse getUserById(Long id) {
        return userRepository.findByIdUser(id).orElseThrow(()->new NotFoundException("User with id: " + id + "is not fount"));
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public SimpleResponse deleteUser(Long id) {
        if(id!=1L) {
            User user = userRepository.findById(id).orElseThrow(() ->
                    new NotFoundException(String.format("Author with email :%s already exists", id)));
            userRepository.deleteById(id);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно")
                    .build();
        }else {
            throw new BadRequestException("user by id 1L dont deleted");
        }
    }

    @Override
    public SimpleResponse assign(Long userId, Long resId) {
        return null;
    }

    @Override
    public SimpleResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Author with email :%s already exists", id)));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDateOfBirth(ZonedDateTime.from(userRequest.getDateOfBirth()));
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRole(userRequest.getRole());
        user.setExperience(userRequest.getExperience());
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }


}
