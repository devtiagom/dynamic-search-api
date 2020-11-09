package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.domain.Company;
import io.github.devtiagom.dynamicsearch.repositories.CompanyRepository;
import io.github.devtiagom.dynamicsearch.resources.dtos.CompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyResponseDTO> getAllCompanies(String name) {
        List<Company> companies = name.isEmpty()
                ? this.companyRepository.findAll()
                : this.companyRepository.findByNameContainingIgnoreCase(name);
        return companies.stream().map(CompanyResponseDTO::new).collect(Collectors.toList());
    }
}
