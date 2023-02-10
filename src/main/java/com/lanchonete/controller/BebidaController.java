package com.lanchonete.controller;

import com.lanchonete.model.Bebida;
import com.lanchonete.repository.BebidaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bebidas")
public class BebidaController {
    @Autowired
    BebidaRepository bebidaRepository;

    @GetMapping
    public List<Bebida> listBebida() {
        return bebidaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBebida(@PathVariable(name = "id") long id) {
        Optional<Bebida> bebidaOptional = bebidaRepository.findById(id);
        return bebidaOptional.<ResponseEntity<Object>>map(bebida -> ResponseEntity.status(HttpStatus.OK).body(bebida)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bebida not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBebida(@RequestBody @Valid Bebida bebidaDto) {
        Bebida bebida = new Bebida();
        BeanUtils.copyProperties(bebidaDto, bebida);

        return ResponseEntity.status(HttpStatus.CREATED).body(bebidaRepository.save(bebida));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody @Valid Bebida bebida) {
        Optional<Bebida> bebidaOptional = bebidaRepository.findById(id);
        if (!bebidaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bebida not found");
        }

        Bebida bebidaUpdt = new Bebida();
        BeanUtils.copyProperties(bebida, bebidaUpdt);
        bebidaUpdt.setId(bebidaOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(bebidaRepository.save(bebidaUpdt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Bebida> bebidaOptional = bebidaRepository.findById(id);
        if (!bebidaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bebida not found");
        }
        bebidaRepository.delete(bebidaOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //Legal
    }
}
