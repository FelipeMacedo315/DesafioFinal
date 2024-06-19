package com.example.omed.Repository;

import com.example.omed.Entities.ClubeEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubeRepository extends JpaRepository<ClubeEntitie, Long> {
    Optional<ClubeEntitie> findByNome(String nome);

    @Query(value = "SELECT * FROM clube_entitie ce WHERE ce.nome = :nome AND ce.uf = :uf", nativeQuery = true)
    Optional<ClubeEntitie> findByNomeAndUf(@Param("nome") String nome, @Param("uf") String uf);


}
