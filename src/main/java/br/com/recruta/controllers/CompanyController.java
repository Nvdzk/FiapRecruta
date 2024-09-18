package br.com.recruta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompany() {

        List<Company> result = (List<Company>) dao.findAll();

        if (result.size() == 0) {
            return ResponseEntity.status(404).build();

        }

        else {

            return ResponseEntity.ok(result);

        }
    }

/* 
    //O que o metodo Post deve fazer na empresa?
    @PostMapping("/login") //login???
	public ResponseEntity<Company> login(@RequestBody Company company) {
		String email = company.getEmail(); //Ele esta buscando algo, o que exatamente???  Email / Senha???
		String senha = company.getSenha();
		
		Company result = dao.findByEmailAndSenha(email, senha);
				
				if(result == null) {
					
					return ResponseEntity.status(404).build();
				}
				
				else {
					
					return ResponseEntity.ok(result);
				}
				
	}
*/
    @DeleteMapping("/company/{code}")
    public ResponseEntity<Company> deleteCompany(@PathVariable int code) {
        try {
            Company result = dao.findById(code).orElse(null);

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