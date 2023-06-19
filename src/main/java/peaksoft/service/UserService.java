package peaksoft.service;



import peaksoft.dto.Authentication.AuthenticationResponse;

import peaksoft.dto.Authentication.SignInRequest;
import peaksoft.dto.Authentication.SignUpRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse getUserById(Long id);
    List<UserResponse> getAllUser();
    SimpleResponse deleteUser(Long id);
    SimpleResponse assign(Long userId,Long resId);
    SimpleResponse updateUser(Long id, UserRequest userRequest);
}
