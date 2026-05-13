package br.com.blog.api.core.services;

import br.com.blog.api.api.dto.auth.LoginRequestDTO;
import br.com.blog.api.api.dto.auth.RegisterResponseDTO;
import br.com.blog.api.api.dto.token.TokenDTO;
import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.core.domain.User;
import br.com.blog.api.core.domain.UserRole;
import br.com.blog.api.core.mapper.UserMapper;
import br.com.blog.api.core.repositories.UserRepository;
import br.com.blog.api.infrastructure.exception.DuplicateResourceException;
import br.com.blog.api.infrastructure.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

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

    public RegisterResponseDTO register(UserCreateRequestDTO request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateResourceException("Username already exists: " + request.username());
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already exists: " + request.email());
        }

        String encryptedPassword = passwordEncoder.encode(request.password());

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        user.setPassword(encryptedPassword);
        user.setBio(request.bio());
        user.setEmail(request.email());
        user.setRole(UserRole.USER);
        user.setCreatedAt(OffsetDateTime.now());

        var savedUser = userRepository.save(user);

        return userMapper.toRegisterResponseDTO(savedUser);
    }

    public TokenDTO login(LoginRequestDTO request) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return jwtTokenProvider.createAccessToken(userDetails.getUsername(), roles);
    }
}


