package com.embarkx.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id){
        companyService.updateCompany(company, id);
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company =  companyService.getCompanyById(id);
        if(company != null)
        return new ResponseEntity<>(company, HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted = companyService.deleteCompanyById(id);
        if(isDeleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);

    }
}
