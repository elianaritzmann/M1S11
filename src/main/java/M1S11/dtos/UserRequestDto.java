package M1S11.dtos;

import M1S11.enums.UserStatus;

public record UserRequestDto(
        String name,
        String username,
         String password,
        UserStatus profile
){


}
