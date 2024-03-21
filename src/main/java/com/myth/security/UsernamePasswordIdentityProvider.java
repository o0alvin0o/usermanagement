package com.myth.security;

import com.myth.entity.User;
import com.myth.service.UserService;
import com.myth.utils.PasswordUtils;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

import java.time.LocalDateTime;


@ApplicationScoped
public class UsernamePasswordIdentityProvider implements IdentityProvider<UsernamePasswordAuthenticationRequest> {

    @Inject
    UserService userService;

    @Override
    public Class<UsernamePasswordAuthenticationRequest> getRequestType() {
        return UsernamePasswordAuthenticationRequest.class;
    }

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> authenticate(UsernamePasswordAuthenticationRequest request, AuthenticationRequestContext context) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
                    String username = request.getUsername();
                    String rawPass = new String(request.getPassword().getPassword());
                    User dbUser = userService.getUser(username);
                    if(PasswordUtils.verify(rawPass, dbUser.getSalt(), dbUser.getHashedPassword())) {
                        dbUser.setLastLogin(LocalDateTime.now());
                        userService.updateUser(dbUser);
                        return UserDetailsService.createSecurityIdentity(dbUser);
                    } else {
                        throw new AuthenticationFailedException("Invalid credentials");
                    }
                })).runSubscriptionOn(Infrastructure.getDefaultExecutor());

    }
}
