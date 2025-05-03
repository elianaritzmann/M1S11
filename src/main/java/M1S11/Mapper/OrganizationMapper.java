package M1S11.Mapper;

import M1S11.dtos.OrganizationRequestDto;
import M1S11.dtos.OrganizationResponseDto;
import M1S11.entities.OrganizationEntity;

import java.util.List;

public class OrganizationMapper {
    
    public static OrganizationResponseDto responseDto (OrganizationEntity organization){
        return new OrganizationResponseDto(
                organization.getId(),
                organization.getName(),
                organization.getContact()
                );
    }

    public static List<OrganizationResponseDto> responseDtos (List<OrganizationEntity>organization){
        return organization.stream().map(OrganizationMapper::responseDto).toList();
    }

    public static OrganizationEntity toEntity(OrganizationEntity organization, OrganizationRequestDto dto) {
        organization.setName(dto.name());
        organization.setContact(dto.contact());

        return organization;

    }


}

