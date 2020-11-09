package io.github.devtiagom.dynamicsearch.resources;

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
}
