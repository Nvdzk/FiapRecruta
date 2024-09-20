// package br.com.recruta.beans;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name="TB_COMPANY")
// public class Company {

// 	@Column(name="id")
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private int id;
	
// 	@Column(name="nome", length = 50)
// 	private String nome;

// 	public int getId() {
// 		return id;
// 	}

// 	public void setId(int id) {
// 		this.id = id;
// 	}

// 	public String getNome() {
// 		return nome;
// 	}

// 	public void setNome(String nome) {
// 		this.nome = nome;
// 	}

// }