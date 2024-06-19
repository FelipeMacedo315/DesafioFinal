package com.example.omed.Services;

import com.example.omed.Entities.ClubeEntitie;
import com.example.omed.Impl.ValidarDados;
import com.example.omed.Repository.ClubeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        Optional<ClubeEntitie> jaExiste = clubeRepository.findByNome(clube.getNome());
        if (jaExiste.isPresent() && camposVazios(jaExiste.get())) {
            System.out.println("ALGUM CAMPO INCORRETO");
            return 400;
        }
        if (jaExiste.isPresent() && estadoValido(jaExiste.get())) {
            System.out.println("ESTADO INVALIDO");
            return 400;

        }
        if (jaExiste.isPresent() && dataFutura(jaExiste.get())) {
            return 400;
        }
        if (jaExiste.isPresent() && Objects.equals(jaExiste.get().getUf(), clube.getUf())) {
            return 409;
        }
        clubeRepository.save(clube);
        return 200;
    }

    public int atualizarClube(Long id, ClubeEntitie clube) {
        Optional<ClubeEntitie> clubeExiste = clubeRepository.findByNome(clube.getNome());

        if (!estadoValido(clube)) {
            System.out.println("passou2");
            return 400;
        }
        if (dataFutura(clube)) {
            System.out.println("passou3");
            return 400;
        }
        if (clubeExiste.isPresent() && Objects.equals(clubeExiste.get().getId(), clube.getId())) {
            return 404;
        }
        Optional<ClubeEntitie> clubeIgual = clubeRepository.findByNomeAndUf(clube.getNome(), clube.getUf());
        if (clubeIgual.isPresent()) {
            clubeIgualNomeEstado(clubeIgual.get(), id);
            if (!clubeIgualNomeEstado(clubeIgual.get(), id)
            ) {
                return 409;
            }
        }
        BeanUtils.copyProperties(clube, clubeExiste);
        clube.setId(id);
        clubeRepository.save(clube);
        return 200;

    }

    public int inativarClube(Long id) {
        Optional<ClubeEntitie> jaExiste = clubeRepository.findById(id);
        if (jaExiste.isEmpty()) {
            return 404;
        }
        jaExiste.get().setAtivo(false);
        clubeRepository.save(jaExiste.get());
        return 204;
    }

    public Optional<ClubeEntitie> buscarClube(Long id) {
        return clubeRepository.findById(id);
    }

}
