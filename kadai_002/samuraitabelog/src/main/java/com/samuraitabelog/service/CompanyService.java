package com.samuraitabelog.service;

import org.springframework.stereotype.Service;

import com.samuraitabelog.entity.Company;
import com.samuraitabelog.form.CompanyEditForm;
import com.samuraitabelog.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;         
    
    public CompanyService(CompanyRepository companyRepository) {        
        this.companyRepository = companyRepository;         
    }
    
    @Transactional
    public void update(CompanyEditForm companyEditForm) {        
        Company company = companyRepository.getReferenceById(companyEditForm.getId());
        
        company.setName(companyEditForm.getName());
        company.setPostalCode(companyEditForm.getPostalCode());
        company.setAddress(companyEditForm.getAddress());
        company.setRepresentative(companyEditForm.getRepresentative());
        company.setFoudingDate(companyEditForm.getFoudingDate());
        company.setTotalAssets(companyEditForm.getTotalAssets());
        company.setBusiness(companyEditForm.getBusiness());
        company.setNumberOfEmployees(companyEditForm.getNumberOfEmployees());
        
        companyRepository.save(company);
    }
}

