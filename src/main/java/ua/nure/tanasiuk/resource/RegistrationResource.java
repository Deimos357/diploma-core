package ua.nure.tanasiuk.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.tanasiuk.model.UserIdentity;
import ua.nure.tanasiuk.service.UserRegistrationService;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping(value = "/v1/registration")
@Api(value = "registration", description = "Operations pertaining to registration")
public class RegistrationResource {
    private final UserRegistrationService userRegistrationService;

    public RegistrationResource(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/email")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Register")
    })
    @ApiOperation("Register")
    public ResponseEntity registration(@RequestBody @Valid UserIdentity userData) {
        UserIdentity user = userRegistrationService.registerUser(userData);
        return ResponseEntity.ok().build();
    }
}
