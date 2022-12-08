package com.lanchonete.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.model.Funcionario;

import com.lanchonete.repository.FuncionarioRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listPedidos() {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePedido(@PathVariable(name = "id") long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        return funcionarioOptional.<ResponseEntity<Object>>map(funcionario -> ResponseEntity.status(HttpStatus.OK).body(funcionario)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario addPedido(@RequestBody @Valid Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") long id,
            @RequestBody @Valid Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (!funcionarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found");
        }

        Funcionario funcionarioUpdt = new Funcionario();
        BeanUtils.copyProperties(funcionario, funcionarioUpdt);
        funcionarioUpdt.setId(funcionarioOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.save(funcionarioUpdt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(name = "id") long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (!funcionarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario not found");
        }
        funcionarioRepository.delete(funcionarioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deleted successfully");
    }

}
