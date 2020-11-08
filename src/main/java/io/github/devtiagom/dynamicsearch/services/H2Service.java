package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.domain.User;
import io.github.devtiagom.dynamicsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class H2Service {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public H2Service(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void H2DatabaseSeeding() {
        User user01 = new User(
                null,
                "Fulana de Tal",
                "fulana.tal@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user02 = new User(
                null,
                "Beltrana de Tal",
                "beltrana.tal@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user03 = new User(
                null,
                "Ciclana de Tal",
                "ciclana.tal@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user04 = new User(
                null,
                "Badanha",
                "badanha@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user05 = new User(
                null,
                "Badanha Jr",
                "badanha.jr@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user06 = new User(
                null,
                "Fulano de Tal",
                "fulano.tal@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user07 = new User(
                null,
                "Beltrano de Tal",
                "beltrano.tal@gmail.com",
                passwordEncoder.encode("1234")
        );

        User user08 = new User(
                null,
                "Ciclano de Tal",
                "ciclano.tal@gmail.com",
                passwordEncoder.encode("1234")
        );

        this.userRepository.saveAll(Arrays.asList(
                user01,
                user02,
                user03,
                user04,
                user05,
                user06,
                user07,
                user08
        ));
    }
}
