package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.resources.dtos.UserRequestDTO;
import io.github.devtiagom.dynamicsearch.domain.User;
import io.github.devtiagom.dynamicsearch.repositories.UserRepository;
import io.github.devtiagom.dynamicsearch.resources.dtos.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers(String name) {
        List<User> users = name.isEmpty()
                ? this.userRepository.findAll()
                : this.userRepository.findByNameContainingIgnoreCase(name);
        return users.stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    public UserResponseDTO getOneUserById(Long id) {
        return new UserResponseDTO(getUserModelById(id));
    }

    public UserResponseDTO saveNewUser(UserRequestDTO userDTO) {
        User newUser = this.userRepository.save(new User(
                null,
                userDTO.getName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword())
        ));
        return new UserResponseDTO(newUser);
    }

    public UserResponseDTO updateUserById(Long id, UserRequestDTO userDTO) {
        User userFromDB = this.getUserModelById(id);
        userFromDB.setName(userDTO.getName());
        userFromDB.setEmail(userDTO.getEmail());
        userFromDB.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        this.userRepository.save(userFromDB);
        return new UserResponseDTO(userFromDB);
    }

    public void deleteUserById(Long id) {
        User userFromDB = this.getUserModelById(id);
        this.userRepository.delete(userFromDB);
    }

    private User getUserModelById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        });
    }
}
