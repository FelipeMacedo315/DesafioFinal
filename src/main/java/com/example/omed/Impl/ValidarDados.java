package com.example.omed.Impl;

import com.example.omed.Entities.ClubeEntitie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public interface ValidarDados {
    default boolean dadosVazios(ClubeEntitie clube) {
        ArrayList<String> estados = new ArrayList<>(List.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA"
                , "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        ));

        if (clube.getNome() == null || clube.getNome().length() <= 2 || clube.getAtivo() == null || clube.getUf() == null || clube.getCriadoEm() == null) {
            return false;
        }
        boolean ufOk = estados.stream().anyMatch(element -> Objects.equals(element, clube.getUf()));
        if (!ufOk) {
            return false;
        }
        LocalDate dataHoje = LocalDate.now();
        if (dataHoje.isBefore(clube.getCriadoEm())) {
            return false;
        }
        return true;
    }
}
