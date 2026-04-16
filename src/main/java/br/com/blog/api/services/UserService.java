package br.com.blog.api.services;

import br.com.blog.api.dto.user.UserResponseDTO;
import br.com.blog.api.entities.User;
import br.com.blog.api.exception.ResourceNotFoundException;
import br.com.blog.api.mapper.UserMapper;
import br.com.blog.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO findById(Long id) {

        logger.info("Finding user with ID: {}", id);

        User entity =  userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("user", id)
                );

        return userMapper.toResponseDTO(entity);
    }

    public UserResponseDTO findByUsername(String username) {

        logger.info("Finding user by username: {}", username);

        var entity = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new ResourceNotFoundException("user", "username", username)
                );

        return userMapper.toResponseDTO(entity);
    }

    public UserResponseDTO

}
