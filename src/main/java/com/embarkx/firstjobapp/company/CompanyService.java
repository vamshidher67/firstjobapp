package com.embarkx.firstjobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void createCompany(Company company);
    boolean updateCompany(Company company, Long id);

    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);
}