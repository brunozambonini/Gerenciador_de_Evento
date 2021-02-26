package com.bruno.gerenciador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pessoas")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Nome não pode ser em branco")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	
	@NotBlank(message = "Sobrenome não pode ser em branco")
	@NotEmpty(message = "Sobrenome não pode ser vazio")
	private String sobrenome;
	
	@ManyToOne
	private Sala sala1;
	
	@ManyToOne
	private Sala sala2;
	
	@ManyToOne
	private EspacoCafe espacoCafe1;
	
	@ManyToOne
	private EspacoCafe espacoCafe2;

	
	public Pessoa() {
		super();
	}

	public Pessoa(long id, String nome, String sobrenome) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Sala getSala1() {
		return sala1;
	}

	public void setSala1(Sala sala1) {
		this.sala1 = sala1;
	}

	public Sala getSala2() {
		return sala2;
	}

	public void setSala2(Sala sala2) {
		this.sala2 = sala2;
	}

	public EspacoCafe getEspacoCafe1() {
		return espacoCafe1;
	}

	public void setEspacoCafe1(EspacoCafe espacoCafe1) {
		this.espacoCafe1 = espacoCafe1;
	}

	public EspacoCafe getEspacoCafe2() {
		return espacoCafe2;
	}

	public void setEspacoCafe2(EspacoCafe espacoCafe2) {
		this.espacoCafe2 = espacoCafe2;
	}
	
}
