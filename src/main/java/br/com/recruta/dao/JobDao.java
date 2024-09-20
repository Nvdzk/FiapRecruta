
package br.com.recruta.dao;


import org.springframework.data.repository.CrudRepository;

import br.com.recruta.beans.Job;


public interface JobDao extends CrudRepository<Job, Integer>{
    
}
