package ua.nure.tanasiuk.resource;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.nure.tanasiuk.dto.ChangePasswordDto;
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
        @ApiResponse(code = 200, message = "Current user profile was retrieved"),
        @ApiResponse(code = 400, message = "Getting current user profile was failed")
    })
    @GetMapping("/me")
    @Authorization("Bearer")
    public ResponseEntity<UserIdentity> getCurrentUserProfile(@RequestParam Long requestInitiatorId) {
        return ResponseEntity.ok(userService.getCurrentUserProfile(requestInitiatorId));
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @PostMapping("/change-password")
    @Authorization("Bearer")
    public ResponseEntity changePassword(@RequestParam Long requestInitiatorId,
                                         @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto, requestInitiatorId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @PutMapping("/me")
    @Authorization("Bearer")
    public ResponseEntity editProfile(@RequestParam Long requestInitiatorId,
                                      @RequestBody UserIdentity userData) {
        userService.editProfile(userData, requestInitiatorId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    @DeleteMapping("/me")
    @Authorization("Bearer")
    public ResponseEntity deleteProfile(@RequestParam Long requestInitiatorId) {
        userService.deleteProfile(requestInitiatorId);
        return ResponseEntity.ok().build();
    }
}
