package com.example.omed.Impl;

import com.example.omed.Entities.ClubeEntitie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public interface ValidarDados {

    public default Boolean camposVazios(ClubeEntitie clube) {
        return clube.getNome() != null && clube.getNome().length() > 2 && clube.getAtivo() != null && clube.getUf() != null && clube.getCriadoEm() != null;
    }

    public default Boolean estadoValido(ClubeEntitie clube) {
        ArrayList<String> estados = new ArrayList<>(List.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA"
                , "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        ));
        return estados.stream().anyMatch(element -> Objects.equals(element, clube.getUf()));
    }

    public default Boolean dataFutura(ClubeEntitie clube) {
        LocalDate dataHoje = LocalDate.now();
        return dataHoje.isBefore(clube.getCriadoEm());
    }

    public default Boolean clubeIgualNomeEstado(ClubeEntitie clube, long id) {
        return clube.getId() == id;
    }

}
