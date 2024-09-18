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

import br.com.recruta.beans.Job;
import br.com.recruta.dao.JobDao;

@RestController
@CrossOrigin("*")
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobDao dao;

    @GetMapping("/job")
    public ResponseEntity<List<Job>> getAllJobs() {

        List<Job> result = (List<Job>) dao.findAll();

        if (result.size() == 0) {
            return ResponseEntity.status(404).build();

        }

        else {

            return ResponseEntity.ok(result);

        }
    }
    
/* 
    //O que o metodo Post deve fazer na vaga de emprego?
    @PostMapping("/login") //login???
	public ResponseEntity<Job> login(@RequestBody Job job) {
		String email = job.getEmail(); //Ele esta buscando algo, o que exatamente???  Email / Senha???
		String senha = job.getSenha();
		
		Job result = dao.findByEmailAndSenha(email, senha);
				
				if(result == null) {
					
					return ResponseEntity.status(404).build();
				}
				
				else {
					
					return ResponseEntity.ok(result);
				}
				
	}
*/

    @DeleteMapping("/job/{code}")
    public ResponseEntity<Job> deleteJob(@PathVariable int code) {
        try {
            Job result = dao.findById(code).orElse(null);

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
