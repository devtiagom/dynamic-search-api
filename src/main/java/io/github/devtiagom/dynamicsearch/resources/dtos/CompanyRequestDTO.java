package io.github.devtiagom.dynamicsearch.resources.dtos;

public class CompanyRequestDTO {

    private String name;

    public CompanyRequestDTO() {}

    public CompanyRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
