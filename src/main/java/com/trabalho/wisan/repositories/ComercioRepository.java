package com.trabalho.wisan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.wisan.entities.Comercio;

public interface ComercioRepository extends JpaRepository<Comercio, Long> {

	List<Comercio> findByNome(String nome);

	// Outro exemplo: pesquisa por endereço
	List<Comercio> findByEndereco(String endereco);
}
