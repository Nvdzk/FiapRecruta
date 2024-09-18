package br.com.recruta.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.recruta.beans.User;


public class UserDao extends CrudRepository<User, Integer>{
	
	public User findByEmailAndSenha(String email, String senha);
	
	public List<User> findByNomeLike(String nome);

}


