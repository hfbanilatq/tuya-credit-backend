package co.edu.eafit.tuya.security.service;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.security.dto.JwtDto;
import co.edu.eafit.tuya.security.dto.LoginUserDto;
import co.edu.eafit.tuya.security.dto.RegisterUserDto;
import co.edu.eafit.tuya.security.dto.UserDto;
import co.edu.eafit.tuya.security.enums.RoleName;
import co.edu.eafit.tuya.security.jwt.JwtProvider;
import co.edu.eafit.tuya.security.model.User;
import co.edu.eafit.tuya.security.model.UserRole;
import co.edu.eafit.tuya.security.repository.UserRepository;
import co.edu.eafit.tuya.service.documenttype.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private DocumentTypeService documentTypeService;
    private JwtProvider jwtProvider;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setDocumentTypeService(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GenericResponseDto register(RegisterUserDto registerUserDto) {

        if (this.userRepository.existsByDocumentNumber(registerUserDto.getDocumentNumber())) {
            return new GenericResponseDto(false, "El usuario ya existe en base de datos", registerUserDto);
        }

        if (this.userRepository.existsByEmail(registerUserDto.getEmail())) {
            return new GenericResponseDto(false, "El email ingresado ya se encuentra en uso", registerUserDto);
        }

        User user = new User(registerUserDto.getName(), registerUserDto.getSurname(), registerUserDto.getDocumentNumber().toLowerCase(), registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).orElseThrow());
        if (registerUserDto.getRoles().contains("ADMIN")) {
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).orElseThrow());
        }
        user.setRoles(roles);
        user.setDocumentType(this.documentTypeService.findById(registerUserDto.getDocumentType()).orElseThrow());
        User registeredUser = userRepository.save(user);


        return new GenericResponseDto(true, "El email ingresado ya se encuentra en uso", this.mapToUserDto(registeredUser));
    }

    public GenericResponseDto login(LoginUserDto loginUserDto) {
        try {

            if (!this.userRepository.existsByDocumentType_IdAndDocumentNumber(loginUserDto.getDocumentTypeId(), loginUserDto.getDocumentNumber().toLowerCase())) {
                new GenericResponseDto(false, "El nombre de usuario no existe", null);

            }

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getDocumentNumber().toLowerCase(), loginUserDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateToken(authentication);

            JwtDto jJwtDto = new JwtDto(jwt);

            return new GenericResponseDto(false, "Inicio de sesión corecto", jJwtDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new GenericResponseDto(false, "Nombre de usuario o contraseña incorrectos", e);
        }
    }

    public Optional<User> getByDocumentNumber(String documentNumber) {
        return this.userRepository.findByDocumentNumber(documentNumber);
    }

    public boolean existsByUserName(String userName) {
        return this.userRepository.existsByDocumentNumber(userName);
    }


    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .documentNumber(user.getDocumentNumber())
                .name(user.getName())
                .surname(user.getSurname())
                .roles(user.getRoles().stream().map(userRole -> userRole.getRoleName().name()).collect(Collectors.toSet()))
                .email(user.getEmail())
                .build();
    }

}
