package br.com.recruta.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.recruta.beans.User;

public interface UserDao extends CrudRepository<User, Integer> {

	public User findByEmailAndPassword(String email, String password);

	public List<User> findByNameStartingWith(String name);

}
