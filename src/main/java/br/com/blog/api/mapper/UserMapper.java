package br.com.blog.api.mapper;

import br.com.blog.api.dto.user.UserCreateRequestDTO;
import br.com.blog.api.dto.user.UserResponseDTO;
import br.com.blog.api.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    User toEntity(UserCreateRequestDTO dto);

    void updateEntity(UserCreateRequestDTO dto, @MappingTarget User user);
}
