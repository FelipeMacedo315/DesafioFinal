package com.example.omed.Repository;

import com.example.omed.Entities.ClubeEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubeRepository extends JpaRepository<ClubeEntitie, Long> {

    Optional<ClubeEntitie> findByNome(String nome);
}
