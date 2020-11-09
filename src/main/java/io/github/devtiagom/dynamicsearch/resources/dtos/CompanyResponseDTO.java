package io.github.devtiagom.dynamicsearch.resources.dtos;

import io.github.devtiagom.dynamicsearch.domain.Company;

public class CompanyResponseDTO {

    private Long id;
    private String name;

    public CompanyResponseDTO() {}

    public CompanyResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyResponseDTO(Company companyModel) {
        this.id = companyModel.getId();
        this.name = companyModel.getName();
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
}
