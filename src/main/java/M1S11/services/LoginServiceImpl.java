package M1S11.services;

import M1S11.Mapper.UserMapper;
import M1S11.configs.JwtConfig;
import M1S11.dtos.LoginRequestDto;
import M1S11.dtos.LoginResponseDto;
import M1S11.dtos.UserRequestDto;
import M1S11.dtos.UserResponseDto;
import M1S11.entities.UserEntity;
import M1S11.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{


    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

// ...

    @Override
    public LoginResponseDto authenticate(LoginRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(), dto.getPassword()
                )
        );

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtConfig.generateToken(dto.getUsername());
        return LoginResponseDto.builder().type("Bearer").token(token).build();
    }

}
