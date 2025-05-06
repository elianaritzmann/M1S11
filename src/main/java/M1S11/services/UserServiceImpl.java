package M1S11.services;

import M1S11.Mapper.UserMapper;
import M1S11.dtos.UserRequestDto;
import M1S11.dtos.UserResponseDto;
import M1S11.entities.UserEntity;
import M1S11.enums.UserStatus;
import M1S11.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD ="admin";
    private final PasswordEncoder encoder;
    private final UserRepository repository;


    public List<UserResponseDto> findAll() {
        List<UserEntity> user = repository.findAll();
        return user.stream().map(UserMapper::responseDto).toList();

    }

    public UserResponseDto findById(Long id) {
        UserEntity user = repository.findById(id).orElseThrow();
        if (user != null){
            return UserMapper.responseDto(user);
        }

        return null;
    }


    public UserResponseDto create(UserRequestDto dto) {
        UserEntity user = new UserEntity();
        UserMapper.toEntity(user, dto);
        user.setPassword(encoder.encode(dto.password()));
        user = repository.save(user);
        return UserMapper.responseDto(user);
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        UserEntity user = repository.findById(id).orElseThrow();
        UserMapper.toEntity(user, dto);
        user.setPassword(encoder.encode(dto.password()));

        user = repository.save(user);
        return UserMapper.responseDto(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        if (username.equals(DEFAULT_USER)){
            return UserEntity.builder().id(0L).username("root").password(encoder.encode(DEFAULT_PASSWORD)).profile(UserStatus.ADMIN).build();
        }
        throw new UsernameNotFoundException(username);
    }
}
