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
@CrossOrigin("*")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao dao;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> result = (List<User>) dao.findAll();

        if (result.size() == 0) {
            return ResponseEntity.status(404).build();

        }

        else {

            return ResponseEntity.ok(result);

        }
    }

    @GetMapping("/user/{code}")
	public ResponseEntity<User> findUser(@PathVariable int code) {
		
		User result = dao.findById(code).orElse(null);
		
		if(result == null) {
			
			return ResponseEntity.status(404).build();
		}
		
		else {
			
			return ResponseEntity.ok(result);
		}
		
	}
    
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        try {
            dao.save(user);
            return ResponseEntity.ok(user);
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }

    @PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		String email = user.getEmail();
		String senha = user.getSenha();
		
		User result = dao.findByEmailAndSenha(email, senha);
				
				if(result == null) {
					
					return ResponseEntity.status(404).build();
				}
				
				else {
					
					return ResponseEntity.ok(result);
				}
				
	}

    // Criar um Put ou atualizar pelo Post???
    @PutMapping("/{id}")
    private void updateUser() {

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