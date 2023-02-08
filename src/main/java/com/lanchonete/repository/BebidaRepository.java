package com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.model.Bebida;

import java.util.List;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long>{

}
