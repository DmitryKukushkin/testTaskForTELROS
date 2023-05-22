package com.ds.kukushkin.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JwtPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt){
        return UserPrincipal.builder()
                .userId(UUID.fromString(jwt.getSubject()))
                .email(jwt.getClaim("e").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT decodedJWT){
        var claim = decodedJWT.getClaim("a");
        if(claim.isNull()||claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
