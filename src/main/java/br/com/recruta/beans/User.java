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
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", length = 100)
	private String name;
	
	@Column(name="email", length = 100)
	private String email;
	
	@Column(name="phone", length = 15)
	private String phone;

	@Column(name="password", length = 20)
	private String password;
	
	@Column(name="picture", length = 100)
	private String picture;

	@Column(name="city", length = 50)
	private String city;

	@Column(name="district", length = 50)
	private String district;

	@Column(name="uf", length = 2)
	private String uf;

	@Column(name="gender", length = 50)
	private String gender;

	@Column(name="socialLink", length = 200)
	private String socialLink;

	@Column(name="education", length = 200)
	private String education;

	@Column(name="goals", length = 200)
	private String professionalGoals;

	@Column(name="habilities", length = 200)
	private String tecnicalHabilities;

	@Column(name="experiences", length = 200)
	private String professionalExperiences;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public void setSocialLink(String socialLink) {
		this.socialLink = socialLink;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getProfessionalGoals() {
		return professionalGoals;
	}

	public void setProfessionalGoals(String professionalGoals) {
		this.professionalGoals = professionalGoals;
	}

	public String getTecnicalHabilities() {
		return tecnicalHabilities;
	}

	public void setTecnicalHabilities(String tecnicalHabilities) {
		this.tecnicalHabilities = tecnicalHabilities;
	}

	public String getProfessionalExperiences() {
		return professionalExperiences;
	}

	public void setProfessionalExperiences(String professionalExperiences) {
		this.professionalExperiences = professionalExperiences;
	}

}