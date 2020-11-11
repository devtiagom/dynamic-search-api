package io.github.devtiagom.dynamicsearch.resources.dtos;

import io.github.devtiagom.dynamicsearch.domain.User;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private boolean isAdmin;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String name, String email, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public UserResponseDTO(User userModel) {
        this.id = userModel.getId();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.isAdmin = userModel.isAdmin();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
