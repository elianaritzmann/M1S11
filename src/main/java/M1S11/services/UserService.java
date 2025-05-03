package M1S11.services;

import M1S11.dtos.UserRequestDto;
import M1S11.dtos.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<UserResponseDto> findAll();

    public UserResponseDto findById(Long id);


    public UserResponseDto create(UserRequestDto dto);

    public UserResponseDto update(Long id, UserRequestDto dto);

    public void delete (Long id);


}