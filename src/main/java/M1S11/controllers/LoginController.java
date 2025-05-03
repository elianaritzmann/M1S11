package M1S11.controllers;

import M1S11.dtos.LoginRequestDto;
import M1S11.dtos.LoginResponseDto;
import M1S11.services.LoginService;
import M1S11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService service;

    @PostMapping
    public LoginResponseDto login(@RequestBody LoginRequestDto dto)
    {return service.authenticate(dto);
    }
}
