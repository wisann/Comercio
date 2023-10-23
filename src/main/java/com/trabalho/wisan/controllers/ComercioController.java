package com.trabalho.wisan.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.wisan.entities.Comercio;
import com.trabalho.wisan.repositories.ComercioRepository;

@RestController
@RequestMapping(value = "/comercios")
public class ComercioController {
	private final ComercioRepository comercioRepository;

	@Autowired
	public ComercioController(ComercioRepository comercioRepository) {
		this.comercioRepository = comercioRepository;
	}

	// Endpoint para criar um novo Comercio
	@PostMapping
	public ResponseEntity<Comercio> createComercio(@RequestBody Comercio comercio) {
		Comercio savedComercio = comercioRepository.saveAndFlush(comercio);
		return ResponseEntity.ok(savedComercio);
	}

	// Endpoint para recuperar todos os Comercios
	@GetMapping
	public ResponseEntity<List<Comercio>> getAllComercios() {
		List<Comercio> comercios = comercioRepository.findAll();
		return ResponseEntity.ok(comercios);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Comercio>> searchComercios(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String endereco) {
		List<Comercio> comercios;

		if (nome != null && endereco != null) {
			// Se ambos nome e endereço forem fornecidos, você pode decidir o que fazer.
			// Por exemplo, aqui pesquisaremos por nome e, em seguida, filtraremos os
			// resultados pelo endereço.
			List<Comercio> byNome = comercioRepository.findByNome(nome);
			comercios = byNome.stream().filter(comercio -> comercio.getEndereco().contains(endereco))
					.collect(Collectors.toList());
		} else if (nome != null) {
			// Se apenas o nome for fornecido, pesquise por nome.
			comercios = comercioRepository.findByNome(nome);
		} else if (endereco != null) {
			// Se apenas o endereço for fornecido, pesquise por endereço.
			comercios = comercioRepository.findByEndereco(endereco);
		} else {
			// Se nenhum parâmetro de consulta for fornecido, retorne todos os comércios.
			comercios = comercioRepository.findAll();
		}

		return ResponseEntity.ok(comercios);
	}

	// Endpoint para recuperar um Comercio pelo ID
	@GetMapping("/{id}")
	public ResponseEntity<Comercio> getComercioById(@PathVariable Long id) {
		Optional<Comercio> optionalComercio = comercioRepository.findById(id);
		if (optionalComercio.isPresent()) {
			return ResponseEntity.ok(optionalComercio.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint para atualizar um Comercio pelo ID
	@PutMapping("/{id}")
	public ResponseEntity<Comercio> updateComercio(@PathVariable Long id, @RequestBody Comercio updatedComercio) {
		if (!comercioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		updatedComercio.setId(id);
		Comercio savedComercio = comercioRepository.saveAndFlush(updatedComercio);
		return ResponseEntity.ok(savedComercio);
	}

	// Endpoint para excluir um Comercio pelo ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComercio(@PathVariable Long id) {
		if (!comercioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		comercioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
