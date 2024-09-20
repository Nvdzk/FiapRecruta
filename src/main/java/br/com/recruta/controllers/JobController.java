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

import br.com.recruta.beans.Job;
import br.com.recruta.dao.JobDao;

@RestController
@CrossOrigin("*")
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobDao dao;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> result = (List<Job>) dao.findAll();
        
        if (result.size() == 0) {
            return ResponseEntity.status(404).build();

        }

        else {

            return ResponseEntity.ok(result);

        }
    }

@GetMapping("/{id}")
	public ResponseEntity<Job> getJob(@PathVariable int id) {
		
		Job result = dao.findById(id).orElse(null);
		
		if(result == null) {
			
			return ResponseEntity.status(404).build();
		}
		
		else {
			
			return ResponseEntity.ok(result);
		}
		
	}

    @PostMapping("/create")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {

        try {
            dao.save(job);
            return ResponseEntity.ok(job);
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable int id, @RequestBody Job job) {
        Job existingJob = dao.findById(id).orElse(null);
        if (existingJob == null) {
            return ResponseEntity.status(404).build();
        }
        
        existingJob.setName(job.getName());
        
        dao.save(existingJob);
        return ResponseEntity.ok(existingJob);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable int id) {
        try {
            Job result = dao.findById(id).orElse(null);

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