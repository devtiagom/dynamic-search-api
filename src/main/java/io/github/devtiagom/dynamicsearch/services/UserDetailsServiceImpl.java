package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        io.github.devtiagom.dynamicsearch.domain.User userModel = this.userRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials."));

        String[] roles = userModel.isAdmin()
                ? new String[]{"ADMIN", "USER"}
                : new String[]{"USER"};

        return User.builder()
                .username(userModel.getEmail())
                .password(userModel.getPassword())
                .roles(roles)
                .build();
    }
}
