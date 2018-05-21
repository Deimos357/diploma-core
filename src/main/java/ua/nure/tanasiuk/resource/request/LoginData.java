package ua.nure.tanasiuk.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
