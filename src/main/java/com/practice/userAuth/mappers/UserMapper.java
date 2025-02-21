package com.practice.userAuth.mappers;

import com.practice.userAuth.Model.User;
import com.practice.userAuth.Payload.Request.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "username", source = "signupRequest.username"),
            @Mapping(target = "email", source = "signupRequest.email"),
            @Mapping(target = "password", source = "signupRequest.password"),
            @Mapping(target = "firstName", source = "signupRequest.firstName"),
            @Mapping(target = "middleName", source = "signupRequest.middleName"),
            @Mapping(target = "lastName", source = "signupRequest.lastName"),
            @Mapping(target = "pinCode", source = "signupRequest.pinCode"),
            @Mapping(target = "mobNo", source = "signupRequest.mobNo"),
            @Mapping(target = "adharNo", source = "signupRequest.adharNo")
    })
    User toDto(SignUpRequest signupRequest);

    SignUpRequest requestToUser(User user);


}
