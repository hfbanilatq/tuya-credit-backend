package co.edu.eafit.tuya.security.model;

import co.edu.eafit.tuya.model.DocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true)
    private String documentNumber;

    @NotNull
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    private DocumentType documentType;

    @ManyToMany
    @JoinTable(name = "user_has_user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<UserRole> roles = new HashSet<>();

    public User(String name,String surname, String documentNumber, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.documentNumber = documentNumber;
        this.email = email;
        this.password = password;
    }
}