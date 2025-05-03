package M1S11.repositories;

import M1S11.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository <OrganizationEntity, Long> {
}
