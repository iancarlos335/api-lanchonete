package com.lanchonete.controller;

import com.lanchonete.model.Salgado;
import com.lanchonete.repository.SalgadoRepository;
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
@RequestMapping("/salgados")
public class SalgadoController {
    @Autowired
    private SalgadoRepository salgadoRepository;

    @GetMapping
    public List<Salgado> listSalgado() {
        return salgadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneSalgado(@PathVariable(name = "id") long id) {
        Optional<Salgado> salgadoOptional = salgadoRepository.findById(id);
        return salgadoOptional.<ResponseEntity<Object>>map(salgado -> ResponseEntity.status(HttpStatus.OK).body(salgado)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salgado not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addSalgado(@RequestBody @Valid Salgado salgadoDto) {
        Salgado salgado = new Salgado();
        BeanUtils.copyProperties(salgadoDto, salgado);
        return ResponseEntity.status(HttpStatus.CREATED).body(salgadoRepository.save(salgado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody @Valid Salgado salgado) {
        Optional<Salgado> salgadoOptional = salgadoRepository.findById(id);
        if (!salgadoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salgado not found");
        }

        Salgado salgadoUpdt = new Salgado();
        BeanUtils.copyProperties(salgado, salgadoUpdt);
        salgadoUpdt.setId(salgadoOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(salgadoRepository.save(salgadoUpdt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Salgado> bebidaOptional = salgadoRepository.findById(id);
        if (!bebidaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bebida not found");
        }
        salgadoRepository.delete(bebidaOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //Legal
    }
}
