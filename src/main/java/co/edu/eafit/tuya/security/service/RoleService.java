package co.edu.eafit.tuya.security.service;

import co.edu.eafit.tuya.security.enums.RoleName;
import co.edu.eafit.tuya.security.model.UserRole;
import co.edu.eafit.tuya.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<UserRole> getByRoleName(RoleName roleName) {
        return this.roleRepository.findByRoleName(roleName);
    }

    public void save(UserRole userRole) {
        this.roleRepository.save(userRole);
    }

}
