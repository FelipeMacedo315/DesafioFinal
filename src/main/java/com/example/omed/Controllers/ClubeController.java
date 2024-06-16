package com.example.omed.Controllers;


import com.example.omed.Entities.ClubeEntitie;
import com.example.omed.Services.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ClubeController {
    @Autowired
    private ClubeService clubeService;

    @PostMapping(value = "/cadastrar-clube")
    public ResponseEntity<?> CadastrarClube(@RequestBody ClubeEntitie clube) {
        int status = clubeService.cadastrarClube(clube);
        return ResponseEntity.status(status).build();
    }
}
