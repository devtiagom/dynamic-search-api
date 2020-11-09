package io.github.devtiagom.dynamicsearch.resources;

import io.github.devtiagom.dynamicsearch.resources.dtos.CompanyRequestDTO;
import io.github.devtiagom.dynamicsearch.resources.dtos.CompanyResponseDTO;
import io.github.devtiagom.dynamicsearch.services.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
@Api("REST API Company")
public class CompanyResource {

    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation("Lists all companies filtering (optionally) by name")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponseDTO> listAllCompanies(
            @RequestParam(value = "name", defaultValue = "", required = false) String name
    ) {
        return this.companyService.getAllCompanies(name);
    }

    @ApiOperation("Shows a single company from database")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponseDTO showOneCompanyById(@PathVariable Long id) {
        return this.companyService.getOneCompanyById(id);
    }

    @ApiOperation("Creates new company on database")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDTO createNewCompany(@RequestBody CompanyRequestDTO companyDTO) {
        return this.companyService.saveNewCompany(companyDTO);
    }

    @ApiOperation("Updates an existing company on database")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponseDTO updateCompanyById(@PathVariable Long id, @RequestBody CompanyRequestDTO companyDTO) {
        return this.companyService.updateCompanyById(id, companyDTO);
    }

    @ApiOperation("Deletes an existing company from database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        this.companyService.deleteCompanyById(id);
    }
}
