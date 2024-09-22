package br.com.recruta.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.com.recruta.beans.Job;

public interface JobDao extends CrudRepository<Job, Integer> {

    List<Job> findByNameStartingWith(String name);

    List<Job> findByCreateDateBetween(Date startDate, Date endDate);

    List<Job> findByNameStartingWithAndCreateDateBetween(String name, Date startDate, Date endDate);

    List<Job> findByCompanyId(int companyId);

}