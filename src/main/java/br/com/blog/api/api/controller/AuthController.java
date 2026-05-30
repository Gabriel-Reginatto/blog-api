package br.com.blog.api.api.controller;

import br.com.blog.api.api.docs.AuthControllerDoc;
import br.com.blog.api.api.dto.auth.LoginRequestDTO;
import br.com.blog.api.api.dto.auth.RegisterResponseDTO;
import br.com.blog.api.api.dto.token.TokenDTO;
import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.core.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthControllerDoc {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody UserCreateRequestDTO request) {

        var user = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        var login = authService.login(request);
        return ResponseEntity.ok(login);
    }

}
