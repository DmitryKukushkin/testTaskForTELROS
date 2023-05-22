package com.ds.kukushkin.security;


import com.ds.kukushkin.entity.UserEntity;
import com.ds.kukushkin.exception.ServiceException;
import com.ds.kukushkin.service.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailService implements UserDetailsService {

    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            UserEntity user = userService.findUserEntityByEmail(username);
            return UserPrincipal.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .authorities(List.of(new SimpleGrantedAuthority(user.getRole().getName())))
                    .password(user.getPassword())
                    .build();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("User %s not found", username));
    }
}
