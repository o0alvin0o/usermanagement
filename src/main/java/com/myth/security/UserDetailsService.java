package com.myth.security;

import com.myth.entity.User;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;

import java.util.stream.Collectors;

public class UserDetailsService {

    public static SecurityIdentity createSecurityIdentity(User user) {
        QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder();
        builder.setPrincipal(user::getUsername);
        builder.addRoles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
        return builder.build();
    }

}
