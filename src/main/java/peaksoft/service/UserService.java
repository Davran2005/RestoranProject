package peaksoft.service;



import peaksoft.dto.Authentication.AuthenticationResponse;

import peaksoft.dto.Authentication.SignInRequest;
import peaksoft.dto.Authentication.SignUpRequest;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;

import java.util.List;


public interface UserService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUser();
    String deleteUser(Long id);
    UserResponse updateUser(Long id, UserRequest userRequest);
}
