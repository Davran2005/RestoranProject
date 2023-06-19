package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.Authentication.AuthenticationResponse;
import peaksoft.dto.Authentication.SignInRequest;
import peaksoft.dto.Authentication.SignUpRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userServices;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Operation(description = "token", summary = "get all user ")
    public List<UserResponse> getAllUsers(){
        return userServices.getAllUser();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PutMapping("{id}")
    @Operation(description = "token", summary = "update user id")
    public SimpleResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return userServices.updateUser(id, userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("{id}")
    @Operation(description = "token", summary = "get user by id")
    public UserResponse getUserById(@PathVariable Long id){
        return userServices.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("{id}")
    @Operation(description = "token", summary = "delete user id")
    public SimpleResponse deleteUserById(@PathVariable Long id) {
        return userServices.deleteUser(id);
    }
    }
