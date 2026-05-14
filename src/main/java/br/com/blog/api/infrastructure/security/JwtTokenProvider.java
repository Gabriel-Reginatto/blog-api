package br.com.blog.api.infrastructure.security;

import br.com.blog.api.api.dto.token.TokenDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long validityMilliseconds;

    private Algorithm algorithm;

    @PostConstruct
    protected void init() {
        log.info("Initializing JwtTokenProvider");

        log.debug("JWT expiration configured: {}ms ({} minutes)",
                validityMilliseconds, validityMilliseconds / 6000);

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        this.algorithm = Algorithm.HMAC256(secretKey.getBytes());

        log.debug("Secret key encoded successfully");
        log.info("JwtTokenProvider initialized successfully");
    }

    public TokenDTO createAccessToken(String username, List<String> roles) {

        log.info("Creating access token for user: {}", username);
        log.debug("User roles: {}", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityMilliseconds);

        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        String accessToken = JWT.create()
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuer(issuerUrl)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

        String refreshToken = refreshToken(username, roles);

        log.debug("Access token created, expires in {}", validity);
        log.debug("Refresh token created");
        log.info("Tokens generated successfully for user: {}", username);

        return new TokenDTO(accessToken, refreshToken, "Bearer", validityMilliseconds);
    }

    private String refreshToken(String username, List<String> roles) {

        log.debug("Creating refresh token for user: {}", username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + (validityMilliseconds * 3));

        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        return JWT.create()
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuer(issuerUrl)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

    }


    public String getUsernameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);

        if (authHeader != null && authHeader.startsWith(SecurityConstants.BEARER_PREFIX)) {
            return authHeader.substring(SecurityConstants.BEARER_PREFIX.length()).trim();
        }
        return null;
    }

}
