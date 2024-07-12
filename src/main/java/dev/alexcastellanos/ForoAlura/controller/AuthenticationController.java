package dev.alexcastellanos.ForoAlura.controller;

import dev.alexcastellanos.ForoAlura.domain.user.User;
import dev.alexcastellanos.ForoAlura.domain.user.UserAuthData;
import dev.alexcastellanos.ForoAlura.infra.security.JWTTokenResponseData;
import dev.alexcastellanos.ForoAlura.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid UserAuthData userAuthData){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userAuthData.username(), userAuthData.password());
        var authenticatedUser = authenticationManager.authenticate(authenticationToken);
        String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenResponseData(jwtToken));
    }
}
