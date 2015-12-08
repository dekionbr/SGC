package com.SGC.domain;

import javax.persistence.*;

@Entity
public class ItemOrdem {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Quantidade")
	private int Quantidade;
	
	@ManyToOne
	@JoinColumn(name = "Ordem_Id")
	private Ordem Ordem;
	
	@ManyToOne
	@JoinColumn(name = "Produto_Id")
	private Produto Produto;
	
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
	public Ordem getOrdem() {
		return Ordem;
	}
	public void setOrdem(Ordem ordem) {
		Ordem = ordem;
	}
	public Produto getProduto() {
		return Produto;
	}
	public void setProduto(Produto produto) {
		Produto = produto;
	}
	
}
