package ua.nure.tanasiuk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Date creationDate;
}
