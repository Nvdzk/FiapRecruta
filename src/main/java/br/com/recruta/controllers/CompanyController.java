package br.com.recruta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recruta.beans.Company;
import br.com.recruta.dao.CompanyDao;

@RestController
@CrossOrigin("*")
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyDao dao;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> result = (List<Company>) dao.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Company result = dao.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {

        try {
            dao.save(company);
            return ResponseEntity.ok(company);
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company company) {
        Company existingCompany = dao.findById(id).orElse(null);
        if (existingCompany == null) {
            return ResponseEntity.status(404).build();
        }
    
        existingCompany.setName(company.getName());
        existingCompany.setDescription(company.getDescription());
    
        dao.save(existingCompany);
        return ResponseEntity.ok(existingCompany);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable int id) {
        try {
            Company result = dao.findById(id).orElse(null);

            if (result == null) {
                return ResponseEntity.status(404).build();
            }
            dao.delete(result);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
