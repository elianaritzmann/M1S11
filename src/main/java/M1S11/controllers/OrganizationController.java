package M1S11.controllers;

import M1S11.dtos.OrganizationRequestDto;
import M1S11.dtos.OrganizationResponseDto;
import M1S11.services.OrganizationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

        private final OrganizationServiceImpl service;



        @GetMapping
        public List<OrganizationResponseDto> getAll(){
            return service.findAll();
        }

        @GetMapping("{id}")
        public OrganizationResponseDto getById(@PathVariable Long id){
            return service.findById(id);
        }


        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public OrganizationResponseDto post(@RequestBody OrganizationRequestDto dto){
            return service.create(dto);
        }

        @PutMapping("{id}")
        public OrganizationResponseDto put(@PathVariable Long id,@RequestBody OrganizationRequestDto dto){
            return service.update(id, dto);
        }

        @DeleteMapping("{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@PathVariable Long id){
            service.delete(id);
        }

    }




