package io.github.devtiagom.dynamicsearch.resources;

import io.github.devtiagom.dynamicsearch.resources.dtos.UserRequestDTO;
import io.github.devtiagom.dynamicsearch.resources.dtos.UserResponseDTO;
import io.github.devtiagom.dynamicsearch.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Api(value = "REST API Users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Lists all users filtering (optionally) by name")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> listAllUsers(
            @RequestParam(value = "name", defaultValue = "", required = false) String name
    ) {
        return this.userService.getAllUsers(name);
    }

    @ApiOperation("Shows a single user from database")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO showOneUserById(@PathVariable Long id) {
        return this.userService.getOneUserById(id);
    }

    @ApiOperation("Creates new user on database")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createNewUser(@RequestBody UserRequestDTO userDTO) {
        return this.userService.saveNewUser(userDTO);
    }

    @ApiOperation("Updates an existing user on database")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateUserById(@PathVariable Long id, @RequestBody UserRequestDTO userDTO) {
        return this.userService.updateUserById(id, userDTO);
    }

    @ApiOperation("Deletes an existing user from database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        this.userService.deleteUserById(id);
    }
}
