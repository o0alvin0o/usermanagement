package com.myth.security;

import com.myth.repo.UserRepo;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.security.spi.runtime.AuthenticationFailureEvent;
import io.quarkus.security.spi.runtime.AuthenticationSuccessEvent;
import io.quarkus.security.spi.runtime.AuthorizationFailureEvent;
import io.quarkus.security.spi.runtime.AuthorizationSuccessEvent;
import io.quarkus.security.spi.runtime.SecurityEvent;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;

import java.time.LocalDateTime;


@ApplicationScoped
public class SecurityEventObserver {

    @Inject
    UserRepo userRepo;

    void observeAuthenticationSuccess(@ObservesAsync AuthenticationSuccessEvent event) {
        QuarkusTransaction.begin();
        userRepo.update("lastLogin = ?1 where username = ?2", LocalDateTime.now(), getPrincipalName(event));
        QuarkusTransaction.commit();
        Log.debugf("User '%s' has authenticated successfully", event.getSecurityIdentity().getPrincipal().getName());
    }

    void observeAuthenticationFailure(@ObservesAsync AuthenticationFailureEvent event) {
        RoutingContext routingContext = (RoutingContext) event.getEventProperties().get(RoutingContext.class.getName());
        Log.debugf("Authentication failed, request path: '%s'", routingContext.request().path());
    }

    void observeAuthorizationSuccess(@ObservesAsync AuthorizationSuccessEvent event) {
        String principalName = getPrincipalName(event);
        if (principalName != null) {
            Log.debugf("User '%s' has been authorized successfully", principalName);
        }
    }

    void observeAuthorizationFailure(@Observes AuthorizationFailureEvent event) {
        Log.debugf(event.getAuthorizationFailure(), "User '%s' authorization failed", event.getSecurityIdentity().getPrincipal().getName());
    }

    private static String getPrincipalName(SecurityEvent event) {
        if (event.getSecurityIdentity() != null) {
            return event.getSecurityIdentity().getPrincipal().getName();
        }
        return null;
    }
}
