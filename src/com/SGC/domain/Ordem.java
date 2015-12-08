package com.SGC.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Ordem {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "Data")
	private Date Data;

	@Column(name = "Data_entrega")
	private Date DataEntrega;

	@Column(name = "Status")
	private Status Status;

	@Column(name = "Prioridade")
	private boolean Prioridade;

	@Column(name = "Justificativa", length = 2000)
	private String Justificativa;

	@ManyToOne
	@JoinColumn(name = "Usuario_Id")
	private Funcionario Usuario;

	@ManyToOne
	@JoinColumn(name = "Fornecedor_Id")
	private Fornecedor Fornecedor;

	@OneToMany(mappedBy = "Ordem", cascade = CascadeType.ALL)
	private List<ItemOrdem> ItensOrdem;

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

	public Date getDataEntrega() {
		return DataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		DataEntrega = dataEntrega;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public Funcionario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Funcionario usuario) {
		Usuario = usuario;
	}

	public Fornecedor getFornecedor() {
		return Fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		Fornecedor = fornecedor;
	}

	public boolean isPrioridade() {
		return Prioridade;
	}

	public void setPrioridade(boolean prioridade) {
		Prioridade = prioridade;
	}

	public String getJustificativa() {
		return Justificativa;
	}

	public void setJustificativa(String justificativa) {
		Justificativa = justificativa;
	}

	public List<ItemOrdem> getItensOrdem() {
		return ItensOrdem;
	}

	public void setItensOrdem(List<ItemOrdem> itensOrdem) {
		ItensOrdem = itensOrdem;
	}

	public String ObterDataEntrega() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		if (this.DataEntrega != null)
			return df.format(this.DataEntrega);
		else
			return "";
	}

}
