package br.com.recruta.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.com.recruta.beans.Job;
import br.com.recruta.beans.User;
import br.com.recruta.dao.CompanyDao;
import br.com.recruta.dao.JobDao;
import br.com.recruta.dao.UserDao;
import br.com.recruta.dto.JobFilter;

@RestController
@CrossOrigin("*")
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobDao dao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

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

        if (result == null) {

            return ResponseEntity.status(404).build();
        }

        else {

            return ResponseEntity.ok(result);
        }

    }

    @GetMapping("/{jobId}/users")
    public ResponseEntity<List<User>> getUsersByJob(@PathVariable int jobId) {
        Job job = dao.findById(jobId).orElse(null);
        if (job == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(job.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<Job> registerJob(@RequestBody Job job) {
        try {
            if (job.getCompany() != null && job.getCompany().getId() != 0) {
                Company company = companyDao.findById(job.getCompany().getId()).orElse(null);
                if (company != null) {
                    job.setCompany(company);
                    job.setCompanyDescription(company.getDescription());
                } else {
                    return ResponseEntity.status(404).build();
                }
            }

            dao.save(job);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/{jobId}/apply")
    public ResponseEntity<?> applyForJob(@PathVariable int jobId, @RequestBody User user) {
        Optional<Job> jobOpt = dao.findById(jobId);
        Optional<User> userOpt = userDao.findById(user.getId());

        if (!jobOpt.isPresent() || !userOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Job job = jobOpt.get();
        User existingUser = userOpt.get();

        if (job.getUsers() == null) {
            job.setUsers(new ArrayList<>());
        }

        if (job.getUsers().contains(existingUser)) {
            return ResponseEntity.status(400).body("Usuário já está inscrito nesta vaga.");
        }

        job.getUsers().add(existingUser);

        if (existingUser.getJobs() == null) {
            existingUser.setJobs(new ArrayList<>());
        }
        existingUser.getJobs().add(job);

        dao.save(job);
        userDao.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable int id, @RequestBody Job job) {
        Job existingJob = dao.findById(id).orElse(null);
        if (existingJob == null) {
            return ResponseEntity.status(404).build();
        }

        existingJob.setName(job.getName());
        existingJob.setJobDescription(job.getJobDescription());
        existingJob.setPosition(job.getPosition());
        existingJob.setEducation(job.getEducation());
        existingJob.setCreateDate(job.getCreateDate());
        existingJob.setExpireDate(job.getExpireDate());
        existingJob.setStatus(job.getStatus());

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

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable int companyId) {
        List<Job> jobs = dao.findByCompanyId(companyId);
        if (jobs.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Job>> filterJob(@RequestBody JobFilter filter) {
        List<Job> coll;

        if (filter.getName() != null && filter.getStartDate() == null && filter.getEndDate() == null) {
            coll = dao.findByNameStartingWith(filter.getName());
        } else if (filter.getStartDate() != null && filter.getEndDate() != null && filter.getName() == null) {
            coll = dao.findByCreateDateBetween(filter.getStartDate(), filter.getEndDate());
        } else if (filter.getName() != null && filter.getStartDate() != null && filter.getEndDate() != null) {
            coll = dao.findByNameStartingWithAndCreateDateBetween(filter.getName(), filter.getStartDate(),
                    filter.getEndDate());
        } else {
            return ResponseEntity.status(400).body(List.of());
        }

        if (coll.isEmpty()) {
            return ResponseEntity.status(404).body(List.of());
        }

        return ResponseEntity.ok(coll);
    }

}