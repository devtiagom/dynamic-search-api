package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.domain.Company;
import io.github.devtiagom.dynamicsearch.domain.User;
import io.github.devtiagom.dynamicsearch.repositories.CompanyRepository;
import io.github.devtiagom.dynamicsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class H2Service {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public H2Service(
            UserRepository userRepository,
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void H2DatabaseSeeding() {
        this.usersSeeding();
        this.companiesSeeding();
    }

    private void usersSeeding() {
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

    private void companiesSeeding() {
        Company company01 = new Company(null, "Google LLC");
        Company company02 = new Company(null, "Facebook Inc.");
        Company company03 = new Company(null, "Amazon.com Inc.");
        Company company04 = new Company(null, "Netflix");
        Company company05 = new Company(null, "Space Exploration Technologies Corp.");

        this.companyRepository.saveAll(Arrays.asList(
                company01,
                company02,
                company03,
                company04,
                company05
        ));
    }
}
