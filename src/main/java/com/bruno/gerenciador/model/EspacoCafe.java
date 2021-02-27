package com.bruno.gerenciador.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "espaco_cafe")
public class EspacoCafe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Nome não pode ser em branco")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	private int lotacao;
	
	@OneToMany
    private List<Pessoa> pessoa;
	
	public EspacoCafe() {
		super();
	}

	public EspacoCafe(long id,
			@NotBlank(message = "Nome não pode ser em branco") @NotEmpty(message = "Nome não pode ser vazio") String nome,
			@NotBlank(message = "Lotação não pode ser em branco") @NotEmpty(message = "Lotação não pode ser vazio") int lotacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.lotacao = lotacao;
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

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public List<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<Pessoa> pessoa) {
		this.pessoa = pessoa;
	}
	
}
