package M1S11.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class LoginResponseDto{
       private String type;
       private String token;



}
