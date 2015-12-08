package com.SGC.domain;

import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Entrada {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "Data")
	private Date Data;

	@Column(name = "Numero_lote", length = 80)
	private String NumeroLote;

	@Column(name = "Qtd_lote")
	private int QtdLote;

	@Column(name = "Validade")
	private Date Validade;

	@Column(name = "Valor")
	private Double Valor;

	@ManyToOne
	@JoinColumn(name = "Usuario_Id")
	private Funcionario Usuario;

	@ManyToOne
	@JoinColumn(name = "Produto_Id")
	private Produto Produto;

	@OneToMany(mappedBy = "Entrada", cascade = CascadeType.ALL)
	private List<ItemBaixa> Baixas;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public Funcionario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Funcionario usuario) {
		Usuario = usuario;
	}

	public String getNumeroLote() {
		return NumeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		NumeroLote = numeroLote;
	}

	public int getQtdLote() {
		return QtdLote;
	}

	public void setQtdLote(int qtdLote) {
		QtdLote = qtdLote;
	}

	public Date getValidade() {
		return Validade;
	}

	public void setValidade(Date validade) {
		Validade = validade;
	}

	public Double getValor() {
		return Valor;
	}

	public void setValor(Double valor) {
		Valor = valor;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public List<ItemBaixa> getBaixas() {
		return Baixas;
	}

	public void setBaixas(List<ItemBaixa> baixas) {
		Baixas = baixas;
	}

}
