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

import com.lanchonete.model.Pedido;
import com.lanchonete.repository.PedidoRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@GetMapping
	public List<Pedido> listPedido() {
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOnePedido(@PathVariable(name="id")long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.<ResponseEntity<Object>>map(pedido -> ResponseEntity.status(HttpStatus.OK).body(pedido)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido not found"));
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido addPedido(@RequestBody @Valid Pedido pedidoDto) {
		Pedido pedido= new Pedido();
		BeanUtils.copyProperties(pedidoDto, pedido);
		return pedidoRepository.save(pedido);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(name="id")long id, @RequestBody @Valid Pedido pedido){
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido not found");
		}
		
		Pedido pedidoUpdt = new Pedido();
		BeanUtils.copyProperties(pedido, pedidoUpdt);
		pedidoUpdt.setId(pedidoOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.save(pedidoUpdt));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePedido(@PathVariable(name="id")long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido not found");
		}
		pedidoRepository.delete(pedidoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Pedido deleted successfully");
	}
	
}
