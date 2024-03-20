package com.myth.api;

import com.myth.dto.UserCreationDTO;
import com.myth.dto.UserDTO;
import com.myth.service.UserService;
import com.myth.utils.APIUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/users")

public class UserAPI {
    private static final Logger LOG = Logger.getLogger(UserAPI.class);

    @Inject
    UserService userService;

    @GET
    @Path("/{username}")
    @RolesAllowed("USER")
    public RestResponse<UserDTO> getUser(@PathParam("username") String username) {
        UserDTO userDTO = userService.getUserDTO(username);
        return userDTO != null ? RestResponse.ok(userDTO) : RestResponse.notFound();
    }

    @GET
    @RolesAllowed("USER")
    public RestResponse<List<UserDTO>> getAll(@QueryParam("page") int page,
                                              @QueryParam("pageSize") int pageSize,
                                              @QueryParam("sort") @Pattern(regexp = "^(username|email|status|creationDate|lastLogin)$", message = "Invalid sort field") String sort,
                                              @QueryParam("order") String order) {
        List<UserDTO> userDTOS = userService.getAllUserDTOs(page - 1, pageSize, sort, APIUtils.getSortDirection(order));
        return RestResponse.ok(userDTOS);
    }

    @POST
    @PermitAll
    public RestResponse<Void> createUser(UserCreationDTO userCreationDTO) throws URISyntaxException {
        if (userService.isUsernameExist(userCreationDTO.username())) {
            return RestResponse.status(RestResponse.Status.CONFLICT);
        }
        userService.createUser(userCreationDTO);
        return RestResponse.created(new URI("/user/" + userCreationDTO.username()));
    }

    @ServerExceptionMapper
    public RestResponse<String> mapIllegalArgumentException(IllegalArgumentException x) {
        LOG.error("Server error due to: " + x.getMessage());
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, "Server error: " + x.getMessage());
    }
}
