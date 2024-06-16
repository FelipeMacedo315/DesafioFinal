package com.example.omed.Services;

import com.example.omed.Entities.ClubeEntitie;
import com.example.omed.Impl.ValidarDados;
import com.example.omed.Repository.ClubeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor

public class ClubeService implements ValidarDados {

    @Autowired
    private ClubeRepository clubeRepository;

    public int cadastrarClube(ClubeEntitie clube) {
        System.out.println(dadosVazios(clube));
        Optional<ClubeEntitie> jaExiste = clubeRepository.findByNome(clube.getNome());
        if (!dadosVazios(clube)) {
            return 400;
        }
        if (jaExiste.isPresent() && Objects.equals(jaExiste.get().getUf(), clube.getUf())) {
            return 409;
        }
        clubeRepository.save(clube);
        return 200;
    }
}
