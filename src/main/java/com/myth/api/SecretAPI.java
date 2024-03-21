package com.myth.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.myth.dto.UserCreationDTO;
import com.myth.entity.User;
import com.myth.service.DataGenerator;
import com.myth.service.UserService;
import com.myth.views.UserView;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.jboss.resteasy.reactive.RestResponse;

@Path("stress")
@RolesAllowed("ADMIN")
public class SecretAPI {

    @Inject
    UserService userService;

    @GET
    @Path("/create-user")
    public RestResponse<String> createUser(@QueryParam("quantity") int quantity) {
        int creationCount = 0;
        for (int i = 0; i < quantity; i++) {
            UserCreationDTO u = DataGenerator.fakeUserCreation();
            if (!userService.isUsernameExist(u.username())) {
                userService.createUser(u);
                creationCount++;
            }
        }
        return RestResponse.ok(String.format("%s user(s) created!!", creationCount));
    }


    @JsonView(UserView.Public.class)
    @GET
    @Path("/random-user")
    public RestResponse<User> getRandomUser() {
        return RestResponse.ok(userService.getRandomUser());
    }


}
