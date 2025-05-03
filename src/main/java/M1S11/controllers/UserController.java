package M1S11.controllers;

import M1S11.dtos.UserRequestDto;
import M1S11.dtos.UserResponseDto;
import M1S11.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

        private final UserServiceImpl service;



        @GetMapping
        public List<UserResponseDto> getAll(){
            return service.findAll();
        }

        @GetMapping("{id}")
        public UserResponseDto getById(@PathVariable Long id){
            return service.findById(id);
        }


        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public UserResponseDto post(@RequestBody UserRequestDto dto){
            return service.create(dto);
        }

        @PutMapping("{id}")
        public UserResponseDto put(@PathVariable Long id,@RequestBody UserRequestDto dto){
            return service.update(id, dto);
        }

        @DeleteMapping("{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@PathVariable Long id){
            service.delete(id);
        }

    }




