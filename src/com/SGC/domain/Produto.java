package com.SGC.domain;

import javax.persistence.*;

@Entity
public class Produto {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="Nome", length=45)
	private String Nome;
	
	@Column(name="Descricao", length=80)
	private String Descricao;	
	
	@Column(name="Status")
	private boolean Status;
	
	@Column(name="Qtd_minima")
	private int QtdMinima;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="Tipo_produto", nullable=false, length=1)
	private TipoProduto TipoProduto;
	

	public Produto(){
		this.Descricao = "";
		this.Nome = "";
		this.Status = true;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public int getQtdMinima() {
		return QtdMinima;
	}

	public void setQtdMinima(int qtdMinima) {
		QtdMinima = qtdMinima;
	}

	public TipoProduto getTipoProduto() {
		return TipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		TipoProduto = tipoProduto;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
}
