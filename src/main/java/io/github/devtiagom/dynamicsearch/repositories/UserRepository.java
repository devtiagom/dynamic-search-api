package io.github.devtiagom.dynamicsearch.repositories;

import io.github.devtiagom.dynamicsearch.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByEmail(String email);
}
