package br.com.recruta.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.recruta.beans.User;

public class CompanyController {

    @GetMapping("/usuario")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> resultado = (List<User>) dao.findAll();

        if (resultado.size() == 0) {
            return ResponseEntity.status(404).build();

        }

        else {

            return ResponseEntity.ok(resultado);

        }
    }

    @PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User usuario) {
		String email = user.getEmail();
		String senha = user.getSenha();
		
		User resultado = userdao.findByEmailAndSenha(email, senha);
				
				if(resultado == null) {
					
					return ResponseEntity.status(404).build();
				}
				
				else {
					
					return ResponseEntity.ok(resultado);
				}
    }


    @DeleteMapping("/user/{code}")
    public ResponseEntity<User> deleteUser(@PathVariable int code) {
        try {
            User result = dao.findById(code).orElse(null);

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
