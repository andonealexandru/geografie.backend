package com.geografie.ora_de_geografie.service.impl;

import com.geografie.ora_de_geografie.io.entity.*;
import com.geografie.ora_de_geografie.io.repository.ClassRepository;
import com.geografie.ora_de_geografie.io.repository.RoleRepository;
import com.geografie.ora_de_geografie.io.repository.UserRepository;
import com.geografie.ora_de_geografie.service.UserService;
import com.geografie.ora_de_geografie.shared.UserPrincipal;
import com.geografie.ora_de_geografie.shared.Utils;
import com.geografie.ora_de_geografie.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return UserPrincipal.build(userEntity);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        // add "ELEV" role
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_ELEV);
        roles.add(userRole);
        userEntity.setRoles(roles);

        // add user in class
        userEntity.setClassId(classRepository.findByClassId(userDto.getClassroom()));

        // set everything else
        userEntity.setUserId(utils.generateDummyId(30));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setEnabled(false);
        userEntity.setEmailConfirmationToken(UUID.randomUUID().toString());

        UserEntity storedUserEntity = userRepository.save(userEntity);

        return modelMapper.map(storedUserEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByEmailConfirmatonToken(String confirmationToken) {

        UserEntity userEntity = userRepository.findByEmailConfirmationToken(confirmationToken);

        if (userEntity == null) throw new RuntimeException("User not found");

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        ModelMapper modelMapper = new ModelMapper();

        UserDto response = modelMapper.map(userEntity, UserDto.class);

        response.setClassroom(userEntity.getClassId().getClassId());

        return response;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        ModelMapper modelMapper = new ModelMapper();

        try {
            UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());

            if (userEntity == null) throw new RuntimeException("User not found");

            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setEnabled(userDto.getEnabled());

            UserEntity updatedUserEntity = userRepository.save(userEntity);
            return modelMapper.map(updatedUserEntity, UserDto.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
