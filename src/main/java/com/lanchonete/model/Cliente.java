package com.lanchonete.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_cliente")
    private String nomeCliente;
    
    @Column(name = "cpf_cliente")
    private String cpfCliente;

    @Column(name = "endereco_cliente")
    private String enderecoCliente;

    public Cliente(){

    }

    public Cliente(String nomeCliente, String cpfCliente , String enderecoCliente){
        super();
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.enderecoCliente = enderecoCliente;
    }

    //Get e Set CPF do Cliente 
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    //Get e Set nome do Cliente 
    public String getNomeCliente(){
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }

    //Get e Set CPF do Cliente 
    public String getCpfCliente(){
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente){
        this.cpfCliente = cpfCliente;
    }

    //Get e Set endereço do Cliente 
    public String getEnderecoCliente(){
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente){
        this.enderecoCliente = enderecoCliente;
    }
	
}
