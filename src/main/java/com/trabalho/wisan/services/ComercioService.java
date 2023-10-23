package com.trabalho.wisan.services;

import com.trabalho.wisan.entities.Comercio;
import com.trabalho.wisan.repositories.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioService {

    private final ComercioRepository comercioRepository;

    @Autowired
    public ComercioService(ComercioRepository comercioRepository) {
        this.comercioRepository = comercioRepository;
    }

    public Comercio createComercio(Comercio comercio) {
        return comercioRepository.saveAndFlush(comercio);
    }

    public List<Comercio> getAllComercios() {
        return comercioRepository.findAll();
    }

    public Optional<Comercio> getComercioById(Long id) {
        return comercioRepository.findById(id);
    }

    public Optional<Comercio> updateComercio(Long id, Comercio updatedComercio) {
        if (!comercioRepository.existsById(id)) {
            return Optional.empty();
        }
        updatedComercio.setId(id);
        return Optional.of(comercioRepository.saveAndFlush(updatedComercio));
    }

    public boolean deleteComercio(Long id) {
        if (comercioRepository.existsById(id)) {
            comercioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
