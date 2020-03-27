package com.geografie.ora_de_geografie.service;

import com.geografie.ora_de_geografie.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto getUser(String email);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    UserDto getUserByEmailConfirmatonToken(String confirmationToken);
}
