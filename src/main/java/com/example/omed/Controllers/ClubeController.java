package com.example.omed.Controllers;


import com.example.omed.Entities.ClubeEntitie;
import com.example.omed.Services.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClubeController {
    @Autowired
    private ClubeService clubeService;

    @PostMapping(value = "/cadastrar-clube")
    public ResponseEntity<?> CadastrarClube(@RequestBody ClubeEntitie clube) {
        int status = clubeService.cadastrarClube(clube);
        return ResponseEntity.status(status).build();

    }

    @PutMapping(value = "/atualizar-clube/{id}")
    public ResponseEntity<?> AtualizarClube(@PathVariable Long id, @RequestBody ClubeEntitie clube) {
        int status = clubeService.atualizarClube(id, clube);
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping(value = "/inativar-clube/{id}")
    public ResponseEntity<?> InativarClube(@PathVariable Long id) {
        int status = clubeService.inativarClube(id);
        return ResponseEntity.status(status).build();
    }

    @GetMapping(value = "/buscar-clube/{id}")
    public ResponseEntity<?> BuscarClube(@PathVariable Long id) {
        Optional<ClubeEntitie> clubeEncontrado = clubeService.buscarClube(id);
        if (clubeEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(clubeEncontrado.get());
    }
}
