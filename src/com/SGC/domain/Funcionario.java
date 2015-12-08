package com.SGC.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.*;

import org.hibernate.annotations.Type;


@Entity
public class Funcionario {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "Nome", nullable = false)
	private String Nome;
	
	@Column(name = "RG", nullable = false)
	private String RG;
	
	@Column(name = "CPF", nullable = false)
	private String CPF;
	
	@Column(name = "Endereco", nullable = false)
	private String Endereco;
	
	@Column(name = "Numero", nullable = false)
	private int Numero;
	
	@Column(name = "Cidade", nullable = false)
	private String Cidade;
	
	@Column(name = "UF", nullable = false)
	private String UF;
	
	@Column(name = "Bairro", nullable = false)
	private String Bairro;
	
	@Column(name = "CEP", nullable = false)
	private String CEP;
	
	@Column(name = "Email", nullable = false)
	private String Email;
	
	@Column(name = "Passe", nullable = false)
	private String Passe;
	
	@Column(name = "Senha", nullable = false)
	private String Senha;
	
	@Column(name = "Status", nullable = false)
	@Type(type="boolean")
	private Boolean Status;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private TipoUsuario TipoUsuario;

	public Funcionario() {
		this.Nome = "";
		this.Bairro = "";
		this.Cidade = "";
		this.Email = "";
		this.Endereco = "";
		this.Passe = "";
		this.UF = "";
		this.TipoUsuario = new TipoUsuario();
		this.Status = true;
		this.Senha = "";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getRG() {
		return RG != null ? new String().concat(RG.substring(0, 2) + "."
				+ RG.substring(2, 5) + "." + RG.substring(5, 8) + "-"
				+ RG.substring(8)) : "";
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF != null ? new String().concat(CPF.substring(0, 3) + "."
				+ CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11)) : "";
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getNumero() {
		return Numero != 0 ? Integer.toString(Numero) : "";
	}

	public void setNumero(int numero) {
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
		return CEP != null ? new String().concat(CEP.substring(0, 5) +
										      	"-" +
										      	CEP.substring(5, 8)) : "";
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getPasse() {
		return Passe;
	}

	public void setPasse(String passe) {
		Passe = passe;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}

	public String getEmail() {
		return Email != null ? Email : "";
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public static String Criptografar(String p) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(p.getBytes(), 0, p.length());

			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			System.out.println("Erro Usuario Criptografar: " + p);
			return p;
		}

	}

}
