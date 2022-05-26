package co.edu.eafit.tuya.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalUser implements UserDetails {
    private String name;
    private String documentNumber;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(String name, String documentNumber, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name =name;
        this.documentNumber =documentNumber;
        this.email =email;
        this.password =password;
        this.authorities =authorities;
    }

    public static PrincipalUser build(User user) {
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                        .collect(Collectors.toList());

        return new PrincipalUser(user.getName(), user.getDocumentNumber(), user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return documentNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
