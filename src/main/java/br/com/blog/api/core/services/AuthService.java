package br.com.blog.api.core.services;

import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.core.mapper.UserMapper;
import br.com.blog.api.core.repositories.UserRepository;
import br.com.blog.api.infrastructure.exception.DuplicateResourceException;
import br.com.blog.api.infrastructure.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = tokenProvider;
    }


}


