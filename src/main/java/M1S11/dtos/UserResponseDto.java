package M1S11.dtos;

import M1S11.enums.UserStatus;

public record UserResponseDto(
         Long id,
         String name,
         String username,
         UserStatus profile
){





}
