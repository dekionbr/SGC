package com.SGC.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Baixa {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Data")
	private Date Data;
	
	@Column(name = "Nome_Solicitante")
	private String NomeSolicitante;

	@Column(name = "Matricula")
	private String Matricula;

	@Column(name = "Setor")
	private String Setor;
	
	@Column(name = "Motivo")
	private MotivoBaixa Motivo;
	
	@OneToMany(mappedBy = "Baixa", cascade = CascadeType.ALL)
	private List<ItemBaixa> ItensBaixa;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public String getSetor() {
		return Setor;
	}

	public void setSetor(String setor) {
		Setor = setor;
	}

	public String getNomeSolicitante() {
		return NomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		NomeSolicitante = nomeSolicitante;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public List<ItemBaixa> getItensBaixa() {
		return ItensBaixa;
	}

	public void setItensBaixa(List<ItemBaixa> itensBaixa) {
		ItensBaixa = itensBaixa;
	}

	public MotivoBaixa getMotivo() {
		return Motivo;
	}

	public void setMotivo(MotivoBaixa motivo) {
		Motivo = motivo;
	}

	
	
}
