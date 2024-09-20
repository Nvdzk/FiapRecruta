// package br.com.recruta.beans;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name="TB_JOB")
// public class Job {

// 	@Column(name="id")
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private int id;
	
// 	@Column(name="nome", length = 50)
// 	private String nome;
	
// 	@Column(name="empresa", length = 100)
// 	private String empresa;
	
// 	@Column(name="cargo", length = 100)
// 	private String cargo;

// 	@Column(name="formacao", length = 100)
// 	private String formacao;
	
// 	@Column(name="descricaoVaga", length = 100)
// 	private String descricaoVaga;

// 	@Column(name="descricaoEmpresa", length = 100)
// 	private String descricaoEmpresa;

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

// 	public String getEmpresa() {
// 		return empresa;
// 	}

// 	public void setEmpresa(String empresa) {
// 		this.empresa = empresa;
// 	}

// 	public String getCargo() {
// 		return cargo;
// 	}

// 	public void setCargo(String cargo) {
// 		this.cargo = cargo;
// 	}

// 	public String getFormacao() {
// 		return formacao;
// 	}

// 	public void setFormacao(String formacao) {
// 		this.formacao = formacao;
// 	}

// 	public String getDescricaoVaga() {
// 		return descricaoVaga;
// 	}

// 	public void setDescricaoVaga(String descricaoVaga) {
// 		this.descricaoVaga = descricaoVaga;
// 	}

// 	public String getDescricaoEmpresa() {
// 		return descricaoEmpresa;
// 	}

// 	public void setDescricaoEmpresa(String descricaoEmpresa) {
// 		this.descricaoEmpresa = descricaoEmpresa;
// 	}
	
	

// }