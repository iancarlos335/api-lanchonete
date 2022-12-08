package com.lanchonete.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="bebidas")
public class Bebida { //interessante
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome_bebida")
	@NotNull
	private String nomeBebida;
	
	@Column(name="valor")
	@NumberFormat(style = Style.NUMBER ,pattern = "#.###,##")
	@NotNull
	private double valor;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="imagem")
	private String imagem;
	
	public Bebida() {}
	
	public Bebida(String nomeBebida, double valor, String descricao, String imagem) {
		super();
		this.nomeBebida = nomeBebida;
		this.valor = valor;
		this.descricao = descricao;
		this.imagem = imagem;
	}


	public String getImagem() { //se eu n adicionar esse recurso de construção no get, eu n vou conseguir acessar pelo android
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeBebida() {
		return nomeBebida;
	}

	public void setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
