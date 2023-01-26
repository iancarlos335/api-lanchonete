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


import com.lanchonete.model.Salgado;
import com.lanchonete.repository.SalgadoRepository;

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
	public ResponseEntity<?> getOneSalgado(@PathVariable(name="id")long id) {
		Optional<Salgado> salgadoOptional = salgadoRepository.findById(id);
        return salgadoOptional.<ResponseEntity<Object>>map(salgado -> ResponseEntity.status(HttpStatus.OK).body(salgado)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salgado not found"));
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Salgado addSalgado(@RequestBody @Valid Salgado salgadoDto) {
		Salgado salgado= new Salgado();
		BeanUtils.copyProperties(salgadoDto, salgado);
		return salgadoRepository.save(salgado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id")long id, @RequestBody @Valid Salgado salgado){
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
	public ResponseEntity<?> delete(@PathVariable(name="id")long id) {
		Optional<Salgado> salgadoOptional = salgadoRepository.findById(id);
		if (!salgadoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salgado not found");
		}
		salgadoRepository.delete(salgadoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Salgado deleted successfully");
	}
}
