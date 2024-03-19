package com.myth.repo;

import com.myth.dto.UserDTO;
import com.myth.entity.User;
import com.myth.enums.UserRole;
import io.quarkus.agroal.DataSource;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {


    public List<UserDTO> getAllUserDTOs(int pageIndex, int pageSize, String sortField, Sort.Direction direction, Set<String> roles) {
        PanacheQuery<UserDTO> query =
                find("from User u join u.roles r where r in (?1)", Sort.by(sortField, direction), roles)
                        .page(pageIndex, pageSize)
                        .project(UserDTO.class);
        return query.list();
    }

    public boolean isUsernameExist(String username) {
        return find("username", username).count() > 0;
    }

    public List<User> getAllUsers(int pageIndex, int pageSize, String sortField, Sort.Direction direction) {
        PanacheQuery<User> query =
                find("from User u join u.roles r where r in (?1)", Sort.by(sortField, direction), Set.of(UserRole.USER, UserRole.ADMIN))
                        .page(pageIndex, pageSize);
        return query.list();
    }
}
