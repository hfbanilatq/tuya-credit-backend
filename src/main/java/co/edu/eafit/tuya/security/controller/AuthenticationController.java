package co.edu.eafit.tuya.security.controller;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.security.dto.JwtDto;
import co.edu.eafit.tuya.security.dto.LoginUserDto;
import co.edu.eafit.tuya.security.dto.RegisterUserDto;
import co.edu.eafit.tuya.security.jwt.JwtProvider;
import co.edu.eafit.tuya.security.model.User;
import co.edu.eafit.tuya.security.service.RoleService;
import co.edu.eafit.tuya.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/authentication")
@CrossOrigin("*")
public class AuthenticationController {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            new ResponseEntity<>(new GenericResponseDto(false, "Error en los datos ingresados para el registro", registerUserDto), HttpStatus.BAD_REQUEST);
        }
        GenericResponseDto result = this.userService.register(registerUserDto);
        if (result.getStatus()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            new ResponseEntity<>(new GenericResponseDto(false, "Por favor ingrese todos los datos", loginUserDto), HttpStatus.BAD_REQUEST);
        }
        GenericResponseDto result = this.userService.login(loginUserDto);
        if (result.getStatus()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<GenericResponseDto> refreshToken(@RequestBody JwtDto jwtDto) {
        try {
            String token = jwtDto.getToken();
            String username = this.jwtProvider.getUserNameFromToken(token);

            User user = userService.getByDocumentNumber(username).orElseThrow();
            token = jwtProvider.refreshToken(jwtDto, user.getId());

            JwtDto jwtDto1 = new JwtDto(token);
            return new ResponseEntity<>(new GenericResponseDto(false, "Se ha refrescado la sesión", jwtDto1), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al refrescar token: " + e);
            return new ResponseEntity<>(new GenericResponseDto(false, "Error al refrescar token invalido", e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
