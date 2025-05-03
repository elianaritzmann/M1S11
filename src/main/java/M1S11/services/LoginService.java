package M1S11.services;

import M1S11.dtos.LoginRequestDto;
import M1S11.dtos.LoginResponseDto;
import M1S11.dtos.UserRequestDto;
import M1S11.dtos.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface LoginService {

    public LoginResponseDto authenticate(LoginRequestDto dto);

}