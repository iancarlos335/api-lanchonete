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
@Table(name="salgados")
public class Salgado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome_salgado")
	@NotNull
	private String nomeSalgado;
	
	@Column(name="valor")
	@NumberFormat(style = Style.CURRENCY,pattern = "#.###,##")
	@NotNull
	private double valor;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="imagem")
	private String imagem;
	
	public Salgado() {}

	public Salgado(String nomeSalgado, double valor, String descricao, String imagem) {
		super();
		this.nomeSalgado = nomeSalgado;
		this.valor = valor;
		this.descricao = descricao;
		this.imagem = imagem;
	}
	
	public String getImagem() {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeSalgado() {
		return nomeSalgado;
	}

	public void setNomeSalgado(String nomeSalgado) {
		this.nomeSalgado = nomeSalgado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
