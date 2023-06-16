package peaksoft.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
public class UserResponse{
    private Long id;
    private String fullName;
    private ZonedDateTime dateOfBirth;
    private String email;
    private String phoneNumber;
    private Role role;

    public UserResponse(Long id, String fullName, ZonedDateTime dateOfBirth, String email, String phoneNumber, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
