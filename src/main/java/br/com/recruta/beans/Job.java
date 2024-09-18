package br.com.recruta.beans;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Job {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", length = 50)
	private String nomeVaga;
	
	@Column(name="email", length = 70)
	private String email;
	
	@Column(name="telefone", length = 13)
	private String telefone;

	@Column(name="senha", length = 12)
	private String senha;
	
	@Column(name="foto", length = 100)
	private String foto;


}



// CADASTRO DE UMA VAGA

// Nome da vaga
// Nome da empresa
// cargo
// formação
// descrição da vaga
// descrição da empresa


