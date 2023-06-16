package peaksoft.dto.Authentication;

import lombok.*;
import peaksoft.enums.Role;

@Builder
public record AuthenticationResponse(
        String token,
        String email,
        String role) {
}
