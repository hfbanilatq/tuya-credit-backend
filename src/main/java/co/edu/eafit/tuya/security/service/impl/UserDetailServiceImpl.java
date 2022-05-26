package co.edu.eafit.tuya.security.service.impl;

import co.edu.eafit.tuya.security.model.PrincipalUser;
import co.edu.eafit.tuya.security.model.User;
import co.edu.eafit.tuya.security.repository.UserRepository;
import co.edu.eafit.tuya.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String documentNumber) throws UsernameNotFoundException {
        User user = this.userRepository.findByDocumentNumber(documentNumber).orElseThrow();

        return PrincipalUser.build(user);
    }
}
