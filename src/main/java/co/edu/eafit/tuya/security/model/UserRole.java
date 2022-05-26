package co.edu.eafit.tuya.security.model;

import co.edu.eafit.tuya.security.enums.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public UserRole(@NotNull RoleName roleNome) {
        this.roleName = roleNome;
    }
}
