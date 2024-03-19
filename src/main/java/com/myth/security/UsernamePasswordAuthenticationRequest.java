package com.myth.security;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.security.identity.request.BaseAuthenticationRequest;

import java.util.Map;


public class UsernamePasswordAuthenticationRequest extends BaseAuthenticationRequest implements AuthenticationRequest {

    private final String username;
    private final PasswordCredential password;

    public UsernamePasswordAuthenticationRequest(String username, PasswordCredential password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public PasswordCredential getPassword() {
        return password;
    }

    @Override
    public <T> T getAttribute(String name) {
        return super.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        super.setAttribute(name, value);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return super.getAttributes();
    }
}
