package com.SGC.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class ItemBaixa {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Quantidade")
	private int Quantidade;
	
	@ManyToOne
	@JoinColumn(name = "Baixa_Id")
	private Baixa Baixa;
	
	@ManyToOne
	@JoinColumn(name = "Produto_Id")
	private Produto Produto;
	
	@ManyToOne
	@JoinColumn(name="Entrada_Id")
	private Entrada Entrada;
	
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}

	public Baixa getSaida() {
		return Baixa;
	}

	public void setSaida(Baixa baixa) {
		Baixa = baixa;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public Entrada getEntradas() {
		return Entrada;
	}

	public void setEntradas(Entrada entrada) {
		Entrada = entrada;
	}
	
	
}
