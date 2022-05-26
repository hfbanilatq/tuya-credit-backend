package co.edu.eafit.tuya.security.repository;

import co.edu.eafit.tuya.security.enums.RoleName;
import co.edu.eafit.tuya.security.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    boolean existsByRoleName(RoleName roleName);

    Optional<UserRole> findByRoleName(RoleName roleName);
}
