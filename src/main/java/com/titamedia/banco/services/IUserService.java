package com.titamedia.banco.services;


import com.titamedia.banco.persistence.entities.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    public UserEntity createUser(UserEntity user);
    public List<UserEntity> getAllUsers();
    public Optional<UserEntity> getUserById(Long userId);
    public UserEntity updateUser(Long userId, UserEntity newUser);
}
