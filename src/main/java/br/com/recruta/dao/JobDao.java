package br.com.recruta.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.recruta.beans.Job;


public interface JobDao extends CrudRepository<Job, Integer>{
	
	//Confirmar exatamente o que isso faz / Fará!
	public Job findByEmailAndSenha(String email, String senha);
	
	public List<Job> findByNomeLike(String nome);

}