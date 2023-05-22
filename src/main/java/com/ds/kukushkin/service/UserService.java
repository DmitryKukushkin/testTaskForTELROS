package com.ds.kukushkin.service;

import com.ds.kukushkin.entity.UserEntity;
import com.ds.kukushkin.exception.ServiceException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void addUserEntity(UserEntity userEntity) throws ServiceException;

    void updateUserEntity(UUID userId, UserEntity userEntity) throws ServiceException;

    void removeUserEntity(UUID userIdFromUserPrincipal) throws ServiceException;

    UserEntity getUserEntity(UUID userIdFromUserPrincipal) throws ServiceException;

    UserEntity findUserEntityByEmail(String username) throws ServiceException;

    List<UserEntity> getAllUser();
}
