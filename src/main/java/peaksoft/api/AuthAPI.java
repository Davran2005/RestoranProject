package peaksoft.api;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.Authentication.AuthenticationResponse;
import peaksoft.dto.Authentication.SignInRequest;
import peaksoft.dto.Authentication.SignUpRequest;
import peaksoft.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthAPI {
    private final AuthenticationService service;



    @PostMapping("/singUp")
    @PermitAll
    @Operation(description = "token", summary = "Регистрация")
    public AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return service.signUp(signUpRequest);
    }
    @PostMapping("/signIn")
    @PermitAll
    @Operation(description = "token", summary = "Вход")
    public AuthenticationResponse signIn(@RequestBody SignInRequest signIn){
        return service.signIn(signIn);
    }
}
