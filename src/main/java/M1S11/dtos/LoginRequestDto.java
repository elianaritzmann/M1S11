package M1S11.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder

public class LoginRequestDto{

        private String username;
        private String password;



}
