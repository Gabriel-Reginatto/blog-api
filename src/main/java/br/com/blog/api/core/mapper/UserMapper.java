package br.com.blog.api.core.mapper;

import br.com.blog.api.api.dto.auth.RegisterResponseDTO;
import br.com.blog.api.api.dto.user.request.UserCreateRequestDTO;
import br.com.blog.api.api.dto.user.response.UserResponseDTO;
import br.com.blog.api.api.dto.user.request.UserUpdateRequestDTO;
import br.com.blog.api.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "comments", ignore = true)
    User toEntity(UserCreateRequestDTO request);

    RegisterResponseDTO toRegisterResponseDTO(User user);

    void updateEntity(UserUpdateRequestDTO dto, @MappingTarget User user);
}
