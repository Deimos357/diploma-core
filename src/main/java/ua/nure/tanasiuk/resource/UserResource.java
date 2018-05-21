package ua.nure.tanasiuk.resource;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.tanasiuk.model.UserIdentity;
import ua.nure.tanasiuk.service.UserService;


@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "user", description = "Operations pertaining to users")
@Validated
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Get current user profile")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Current user profile was retrieved")
    })
    @GetMapping("/me")
    @Authorization("Bearer")
    public ResponseEntity<UserIdentity> getCurrentUserProfile(@RequestParam Long requestInitiatorId) {
        return ResponseEntity.ok(userService.getCurrentUserProfile(requestInitiatorId));
    }
}
