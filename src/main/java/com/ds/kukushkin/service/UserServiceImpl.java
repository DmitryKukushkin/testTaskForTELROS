package com.ds.kukushkin.service;

import com.ds.kukushkin.entity.UserEntity;
import com.ds.kukushkin.exception.ServiceErrorCode;
import com.ds.kukushkin.exception.ServiceException;
import com.ds.kukushkin.repository.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    RoleService roleService;

    @Transactional
    @Override
    public void addUserEntity(UserEntity userEntity) throws ServiceException {
        if(!userRepo.existsByEmail(userEntity.getEmail())){
            userEntity.setRole(roleService.getRoleByName("ROLE_USER"));
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
            userRepo.save(userEntity);
        } else{
            throw new ServiceException(ServiceErrorCode.ERROR_CODE_EMAIL_ALREADY_USE);
        }
    }

    @Transactional
    @Override
    public void updateUserEntity(UUID userId, UserEntity userEntityRequest){
        UserEntity userEntityForUpdate = userRepo.findById(userId).get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(userEntityRequest.getBirthday() != null){
            userEntityForUpdate.setBirthday(userEntityRequest.getBirthday());
        }
        if(userEntityRequest.getName() != null){
            userEntityForUpdate.setName(userEntityRequest.getName());
        }
        if(userEntityRequest.getLastname() != null){
            userEntityForUpdate.setLastname(userEntityRequest.getLastname());
        }
        if(userEntityRequest.getPatronymic() != null){
            userEntityForUpdate.setPatronymic(userEntityForUpdate.getPatronymic());
        }
        if(userEntityRequest.getPhoneNumber() !=null){
            userEntityForUpdate.setPhoneNumber(userEntityForUpdate.getPhoneNumber());
        }
        if(userEntityRequest.getPassword() != null){
            userEntityForUpdate.setPassword(bCryptPasswordEncoder.encode(userEntityForUpdate.getPassword()));
        }
        userRepo.save(userEntityForUpdate);
    }

    @Transactional
    @Override
    public void removeUserEntity(UUID userIdFromUserPrincipal) {
        userRepo.deleteById(userIdFromUserPrincipal);
    }

    @Transactional
    @Override
    public UserEntity getUserEntity(UUID userIdFromUserPrincipal) throws ServiceException {
        return userRepo.findById(userIdFromUserPrincipal)
                .orElseThrow(()-> new ServiceException(ServiceErrorCode.ERROR_CODE_NOT_FOUND));
    }

    @Transactional
    @Override
    public UserEntity findUserEntityByEmail(String username) throws ServiceException {
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new ServiceException(ServiceErrorCode.ERROR_CODE_NOT_FOUND));
    }

    @Transactional
    @Override
    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }

}
