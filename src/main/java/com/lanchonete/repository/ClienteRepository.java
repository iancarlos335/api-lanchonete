package com.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanchonete.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { //se der pra escolher entre plural ou singular aqui tbm seria interessante.

}
