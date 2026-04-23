package br.com.blog.api.core.mapper;

import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    User toEntity(UserCreateRequestDTO dto);

    void updateEntity(UserUpdateRequestDTO dto, @MappingTarget User user);
}
