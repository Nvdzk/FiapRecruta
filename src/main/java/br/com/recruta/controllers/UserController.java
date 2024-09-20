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

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> result = (List<User>) dao.findAll();

        if (result.size() == 0) {
            return ResponseEntity.status(404).build();

        }

        else {

            return ResponseEntity.ok(result);

        }
    }

    @GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		
		User result = dao.findById(id).orElse(null);
		
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
		String password = user.getPassword();
		
		User result = dao.findByEmailAndPassword(email, password);
				
				if(result == null) {
					
					return ResponseEntity.status(404).build();
				}
				
				else {
					
					return ResponseEntity.ok(result);
				}
				
	}

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User existingUser = dao.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.status(404).build();
        }
        
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        
        dao.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        try {
            User result = dao.findById(id).orElse(null);

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