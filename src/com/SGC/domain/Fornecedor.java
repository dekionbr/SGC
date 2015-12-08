package com.SGC.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import com.sun.istack.internal.Nullable;

@Entity
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int Id;
	@Column(name = "NomeFantasia")
	private String NomeFantasia;
	@Column(name = "RazaoSocial")
	private String RazaoSocial;
	@Column(name = "CNPJ")
	private String CNPJ;
	@Column(name = "Endereco")
	private String Endereco;
	@Column(name = "Numero")
	private String Numero;
	@Column(name = "Cidade")
	private String Cidade;
	@Column(name = "UF")
	private String UF;
	@Column(name = "Bairro")
	private String Bairro;
	@Column(name = "CEP")
	private String CEP;
	@Column(name = "NomeRepresentante")
	private String NomeRepresentante;
	@Column(name = "Status")
	@Type(type = "boolean")
	private boolean Status;

	@OneToMany(mappedBy = "Fornecedor", cascade = CascadeType.ALL)
	private List<Contato> Contatos;

	public Fornecedor() {
		this.NomeFantasia = "";
		this.RazaoSocial = "";
		this.CNPJ = "";
		this.Endereco = "";
		this.Numero = "";
		this.Cidade = "";
		this.UF = "";
		this.Bairro = "";
		this.CEP = "";
		this.NomeRepresentante = "";
		this.Contatos = new ArrayList<Contato>();
		this.Status = true;

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNomeFantasia() {
		return NomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		NomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return RazaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return !CNPJ.isEmpty() && CNPJ.length() == 14 ? new String().concat(CNPJ.substring(0, 2) + "."
				+ CNPJ.substring(2, 5) + "." + CNPJ.substring(5, 8) + "/"
				+ CNPJ.substring(8, 12) + "-" + CNPJ.substring(12, 14)) : "";
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCEP() {
		return !CEP.isEmpty() && CEP.length() == 8 ? new String().concat(CEP.substring(0, 5) + "-"
				+ CEP.substring(5, 8)) : "";
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getNomeRepresentante() {
		return NomeRepresentante;
	}

	public void setNomeRepresentante(String nomeRepresentante) {
		NomeRepresentante = nomeRepresentante;
	}

	public List<Contato> getContatos() {
		return Contatos;
	}

	public void setContatos(List<Contato> contatos) {
		Contatos = contatos;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}
