package com.myth.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.myth.entity.User;
import com.myth.exception.UsernameNotFoundException;
import com.myth.service.UserService;
import com.myth.utils.APIUtils;
import com.myth.views.UserView;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.List;

@Path("/admin")
@RolesAllowed("ADMIN")
public class AdminAPI {

    @Inject
    UserService userService;

    @GET
    @Path("/users")
    @JsonView(UserView.Public.class)
    public RestResponse<List<User>> getAllUsers(@QueryParam("page") int page,
                                                @QueryParam("pageSize") int pageSize,
                                                @QueryParam("sort") @Pattern(regexp = "^(username|email|status|creationDate|lastLogin)$", message = "Invalid sort field") String sort,
                                                @QueryParam("order") String order) {
        List<User> users = userService.getAllUsers(page - 1, pageSize, sort, APIUtils.getSortDirection(order));
        return RestResponse.ok(users);
    }

    @PATCH
    @Path("/users/activate")
    public RestResponse<Void> activateUser(@QueryParam("username") String username) {
        userService.activateUser(username);
        return RestResponse.noContent();
    }

    @ServerExceptionMapper
    public RestResponse<String> mapUsernameNotFoundException(UsernameNotFoundException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST, x.getMessage());
    }
}
