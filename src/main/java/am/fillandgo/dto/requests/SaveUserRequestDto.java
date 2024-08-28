package am.fillandgo.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * SaveUserRequestDto is a data transfer object class that represents the request DTO for saving user data.
 * It contains the following fields:
 * - userName: A non-empty string representing the username of the user. The length should be between 3 and 30 characters.
 * - password: A non-empty string representing the password of the user. The length should be between 3 and 30 characters.
 * - email: A non-empty string representing the email address of the user. It should be a valid email format.
 * - role: A non-empty string representing the role of the user. The length should be between 3 and 30 characters.
 * The class provides the following methods:
 * - getUserName(): Returns the username of the user.
 * - getPassword(): Returns the password of the user.
 * - getEmail(): Returns the email address of the user.
 * - getRole(): Returns the role of the user.
 * The class also has annotations such as @Data, @AllArgsConstructor, and @Builder for generating common boilerplate code.
 * It also has validation annotations such as @NotEmpty, @Size, and @Email for validating the fields.
 */
@Data
@AllArgsConstructor
@Builder
public class SaveUserRequestDto {

    @NotEmpty(message = "userName can not be a null or empty")
    @Size(min = 3, max = 30, message = "userName length is between 5 and 30")
    private String userName;

    @NotEmpty(message = "password can not be a null or empty")
    @Size(min = 3, max = 30, message = "password length is between 3 and 30")
    private String password;

    @NotEmpty(message = "email can not be a null or empty")
    @Email(message = "email address should be valid email format")
    private String email;

    @NotEmpty(message = "role can not be a null or empty")
    @Size(min = 3, max = 30, message = "role length is between 3 and 30")
    private String role;

}
