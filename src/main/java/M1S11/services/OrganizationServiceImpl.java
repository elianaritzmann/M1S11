package M1S11.services;

import M1S11.Mapper.OrganizationMapper;
import M1S11.dtos.OrganizationRequestDto;
import M1S11.dtos.OrganizationResponseDto;
import M1S11.entities.OrganizationEntity;
import M1S11.repositories.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{

   
    private final OrganizationRepository repository;


    public List<OrganizationResponseDto> findAll() {
        List<OrganizationEntity> Organization = repository.findAll();
        return Organization.stream().map(OrganizationMapper::responseDto).toList();

    }

    public OrganizationResponseDto findById(Long id) {
        OrganizationEntity Organization = repository.findById(id).orElseThrow();
        if (Organization != null){
            return OrganizationMapper.responseDto(Organization);
        }

        return null;
    }

    public OrganizationResponseDto create(OrganizationRequestDto dto) {
        OrganizationEntity Organization = new OrganizationEntity();
        OrganizationMapper.toEntity(Organization, dto);
        Organization = repository.save(Organization);
        return OrganizationMapper.responseDto(Organization);
    }

    public OrganizationResponseDto update(Long id, OrganizationRequestDto dto) {
        OrganizationEntity Organization = repository.findById(id).orElseThrow();
        OrganizationMapper.toEntity(Organization, dto);

        Organization = repository.save(Organization);
        return OrganizationMapper.responseDto(Organization);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    
}
