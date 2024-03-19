package com.myth.security;

import com.myth.entity.User;
import com.myth.enums.UserRole;
import io.quarkus.security.credential.Credential;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusPrincipal;
import io.smallrye.mutiny.Uni;

import java.security.Permission;
import java.security.Principal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is alternative for {@link UserDetailsService} used in {@link UsernamePasswordIdentityProvider} in case you need fine-grained control
 */
public class CustomeSecurityIdentity implements SecurityIdentity {

    private final User user;

    public CustomeSecurityIdentity(User user) {
        this.user = user;
    }

    @Override
    public Principal getPrincipal() {
        return new QuarkusPrincipal(user.getUsername());
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public Set<String> getRoles() {
        return user.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean hasRole(String role) {
        UserRole userRole = UserRole.valueOf(role.toUpperCase());
        return user.getRoles().contains(userRole);
    }

    @Override
    public <T extends Credential> T getCredential(Class<T> credentialType) {
        return null;
    }

    @Override
    public Set<Credential> getCredentials() {
        Credential credential = new PasswordCredential(user.getHashedPassword().toCharArray());
        return Set.of(credential);
    }

    @Override
    public <T> T getAttribute(String name) {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Uni<Boolean> checkPermission(Permission permission) {
        return Uni.createFrom().item(false);
    }
}
