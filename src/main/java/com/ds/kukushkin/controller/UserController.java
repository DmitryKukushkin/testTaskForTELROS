package com.ds.kukushkin.controller;

import com.ds.kukushkin.entity.UserEntity;
import com.ds.kukushkin.exception.ServiceException;
import com.ds.kukushkin.security.UserPrincipal;
import com.ds.kukushkin.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UUID> registerUser(@RequestBody UserEntity userEntity) throws ServiceException {
        userService.addUserEntity(userEntity);
        return ResponseEntity.ok(userEntity.getId());
    }

    @PutMapping("/user")
    public void updateUser(@AuthenticationPrincipal UserPrincipal userPrincipal,
                           @RequestBody UserEntity userEntity) throws ServiceException {
        userService.updateUserEntity(userPrincipal.getUserId(),userEntity);
    }

    @DeleteMapping("/user")
    public void deleteUser(@AuthenticationPrincipal UserPrincipal userPrincipal) throws ServiceException {
        userService.removeUserEntity(userPrincipal.getUserId());
    }

    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUserEntity(@AuthenticationPrincipal UserPrincipal userPrincipal) throws ServiceException {
        return ResponseEntity.ok(userService.getUserEntity(userPrincipal.getUserId()));
    }
}
