package com.trabalho.wisan.exceptions;

public class ComercioNotFoundException extends RuntimeException {
    public ComercioNotFoundException(Long id) {
        super("Comércio não encontrado com o ID: " + id);
    }
}
