package br.com.blog.api.mapper;

import br.com.blog.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    User toEntity(UserCreateRequestDTO dto);

    void updateEntity(UserUpdateRequestDTO dto, @MappingTarget User user);
}
