package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.resources.dtos.UserRequestDTO;
import io.github.devtiagom.dynamicsearch.domain.User;
import io.github.devtiagom.dynamicsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(String name) {
        return name.isEmpty()
                ? this.userRepository.findAll()
                : this.userRepository.findByNameContainingIgnoreCase(name);
    }

    public User getOneUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        });
    }

    public User saveNewUser(UserRequestDTO userDTO) {
        return this.userRepository.save(new User(null, userDTO.getName(), userDTO.getEmail()));
    }

    public User updateUserById(Long id, UserRequestDTO userDTO) {
        User userFromDB = this.getOneUserById(id);
        userFromDB.setName(userDTO.getName());
        userFromDB.setEmail(userDTO.getEmail());
        return this.userRepository.save(userFromDB);
    }

    public void deleteUserById(Long id) {
        User userFromDB = this.getOneUserById(id);
        this.userRepository.delete(userFromDB);
    }
}
