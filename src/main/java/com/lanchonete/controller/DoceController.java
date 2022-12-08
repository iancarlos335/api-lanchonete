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

import com.lanchonete.model.Doce;
import com.lanchonete.repository.DoceRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/doces")
public class DoceController {
	@Autowired
	private DoceRepository doceRepository;
	
	@GetMapping
	public List<Doce> listDoce() {
		return doceRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneDoce(@PathVariable(name="id")long id) {
		Optional<Doce> doceOptional = doceRepository.findById(id);
        return doceOptional.<ResponseEntity<Object>>map(doce -> ResponseEntity.status(HttpStatus.OK).body(doce)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doce not found"));
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Doce addDoce(@RequestBody @Valid Doce doceDto) {
		Doce doce= new Doce();
		BeanUtils.copyProperties(doceDto, doce);
		return doceRepository.save(doce);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(name="id")long id, @RequestBody @Valid Doce doce){
		Optional<Doce> doceOptional = doceRepository.findById(id);
		if (!doceOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doce not found");
		}
		
		Doce doceUpdt = new Doce();
		BeanUtils.copyProperties(doce, doceUpdt);
		doceUpdt.setId(doceOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(doceRepository.save(doceUpdt));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(name="id")long id) {
		Optional<Doce> doceOptional = doceRepository.findById(id);
		if (!doceOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doce not found");
		}
		doceRepository.delete(doceOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Doce deleted successfully");
	}
}
