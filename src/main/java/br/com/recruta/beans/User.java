package br.com.recruta.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_USER")
public class User {
	
	// Define se o usuario é um candidato ou recrutador
	@Column(name = "role", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'user")
	private String role;

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)





// nome
// email
// telefone
// foto


// sexo
// cidade
// estado
// endereco
// link para rede social
// corpo do curriculo 
// formacao academica 