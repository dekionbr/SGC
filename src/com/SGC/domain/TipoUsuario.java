package com.SGC.domain;

import javax.persistence.*;

@Entity
public class TipoUsuario {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Valor")
	private String Valor;
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}

	public String getValor() {
		return Valor;
	}

	public void setValor(String valor) {
		Valor = valor;
	}	
	
}
