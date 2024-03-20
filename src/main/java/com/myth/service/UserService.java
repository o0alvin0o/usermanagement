package com.myth.service;

import com.myth.dto.PasswordChangeDTO;
import com.myth.dto.UserCreationDTO;
import com.myth.dto.UserDTO;
import com.myth.dto.UserUpdateDTO;
import com.myth.entity.User;
import com.myth.enums.UserRole;
import com.myth.enums.UserStatus;
import com.myth.exception.UsernameNotFoundException;
import com.myth.repo.UserRepo;
import com.myth.utils.PasswordUtils;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepo userRepo;

    @Transactional
    public void createUser(UserCreationDTO user) {
        User u = new User();
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hash(user.password(), salt);
        u.setUsername(user.username());
        u.setEmail(user.email());
        u.setRoles(Set.of(UserRole.USER));
        u.setStatus(UserStatus.SUSPENDED);
        u.setSalt(salt);
        u.setHashedPassword(hashedPassword);
        u.setCreationDate(LocalDateTime.now());
        userRepo.persist(u);
    }

    @Transactional
    public void activateUser(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        user.setStatus(UserStatus.ACTIVE);
        userRepo.persist(user);
    }

    public UserDTO deactivateUser(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDTO getUserDTO(String username) {
        return userRepo.find("username", username).project(UserDTO.class).firstResult();
    }

    public User getUser(String username) {
        return userRepo.find("username", username).firstResult();
    }

    public boolean isUsernameExist(String username) {
        return userRepo.isUsernameExist(username);
    }

    public List<UserDTO> getAllUserDTOs(int pageIndex, int pageSize, String sortField, Sort.Direction direction) {
        return userRepo.getAllUserDTOs(pageIndex, pageSize, sortField, direction, Set.of(UserRole.USER.name(), UserRole.TRADER.name()));
    }

    public UserDTO updateUserInformation(String username, UserUpdateDTO userUpdateDTO) {
        return null;
    }

    public void deleteByUsername(String username) {

    }

    public boolean changePassword(PasswordChangeDTO passwordChangeDTO) {
        return false;
    }

    public List<User> getAllUsers(int page, int pageSize, String order, Sort.Direction sortDirection) {
        return userRepo.getAllUsers(page, pageSize, order, sortDirection);
    }
}
