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

import br.com.recruta.beans.User;
import br.com.recruta.dao.UserDao;

@RestController
@RequestMapping("user")
public class UsersController {

    @Autowired
    private UserDao dao;

    // @GetMapping
    // private void getAllUsers(){

    // }
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
    // @PostMapping("/register")
    // private void createUser(){

    // }

    @PostMapping("/register")
    public ResponseEntity<User> salvarUsuario(@RequestBody User user) {

        try {
            dao.save(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }

    @PutMapping("/{id}")
    private void updateUser() {

    }

    // @DeleteMapping("/{id}")
    // private void deleteUser(){

    // }
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
