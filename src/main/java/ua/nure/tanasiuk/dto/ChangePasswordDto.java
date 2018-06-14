package ua.nure.tanasiuk.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;
}
