package M1S11.services;

import M1S11.dtos.OrganizationRequestDto;
import M1S11.dtos.OrganizationResponseDto;


import java.util.List;

public interface OrganizationService  {

    public List<OrganizationResponseDto> findAll();

    public OrganizationResponseDto findById(Long id);


    public OrganizationResponseDto create(OrganizationRequestDto dto);

    public OrganizationResponseDto update(Long id, OrganizationRequestDto dto);

    public void delete (Long id);


}