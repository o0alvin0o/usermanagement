package com.myth.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.myth.enums.UserRole;
import com.myth.enums.UserStatus;
import com.myth.views.UserView;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@UserDefinition
public class User {

    @Id
    @Username
    private String username;

    @Column(name = "hashed_password")
    @Password
    @JsonView(UserView.Private.class)
    private String hashedPassword;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "username"))
    @Column(name = "role")
    private Set<UserRole> roles;

    @Roles
    @JsonView(UserView.Private.class)
    public Set<String> getStringRoles() {
        return roles.stream().map(Enum::toString).collect(Collectors.toSet());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
