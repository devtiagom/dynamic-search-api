package io.github.devtiagom.dynamicsearch.services;

import io.github.devtiagom.dynamicsearch.domain.Company;
import io.github.devtiagom.dynamicsearch.repositories.CompanyRepository;
import io.github.devtiagom.dynamicsearch.resources.dtos.CompanyRequestDTO;
import io.github.devtiagom.dynamicsearch.resources.dtos.CompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public CompanyResponseDTO getOneCompanyById(Long id) {
        return new CompanyResponseDTO(getCompanyModelById(id));
    }

    public CompanyResponseDTO saveNewCompany(CompanyRequestDTO companyDTO) {
        Company newCompany = this.companyRepository.save(new Company(null, companyDTO.getName()));
        return new CompanyResponseDTO(newCompany);
    }

    public CompanyResponseDTO updateCompanyById(Long id, CompanyRequestDTO companyDTO) {
        Company companyFromDB = this.getCompanyModelById(id);
        companyFromDB.setName(companyDTO.getName());
        this.companyRepository.save(companyFromDB);
        return new CompanyResponseDTO(companyFromDB);
    }

    public void deleteCompanyById(Long id) {
        Company companyFromDB = this.getCompanyModelById(id);
        this.companyRepository.delete(companyFromDB);
    }

    private Company getCompanyModelById(Long id) {
        return this.companyRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        });
    }
}
