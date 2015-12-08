package com.SGC.domain;

import javax.persistence.*;

@Entity
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int Id;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "TipoContato")
	private TipoContato TipoContato;

	@Column(name = "Valor")
	private String Valor;

	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "Fornecedor_Id")
	private Fornecedor Fornecedor;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public TipoContato getTipoContato() {
		return TipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		TipoContato = tipoContato;
	}

	public String getValor() {
		return Valor;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public Fornecedor getFornecedor() {
		return Fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		Fornecedor = fornecedor;
	}

}
