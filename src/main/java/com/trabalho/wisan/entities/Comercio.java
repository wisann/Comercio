package com.trabalho.wisan.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trabalho.wisan.enums.ComercioEnums;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "comercios")
public class Comercio {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	private String nome;
	@Enumerated(EnumType.STRING)
	@JsonProperty("categoria")
	private ComercioEnums categoria;
	private String endereco;

	public Comercio() {
		// Construtor vazio é necessário para o JPA
	}

	

	public Comercio(Long id, String nome, ComercioEnums categoria, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.endereco = endereco;
	}



	// Getters e setters

	public ComercioEnums getCategoria() {
		return categoria;
	}

	public void setCategoria(ComercioEnums categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comercio other = (Comercio) obj;
		return Objects.equals(id, other.id);
	}
}
