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
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponseDTO findById(Long id) {

        logger.info("Finding user with ID: {}", id);

        User entity =  repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("user", id)
                );

        return mapper.toResponseDTO(entity);
    }



}
