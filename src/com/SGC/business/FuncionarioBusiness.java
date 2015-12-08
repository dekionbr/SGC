package com.SGC.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.SGC.dao.FuncionarioDAO;
import com.SGC.domain.Funcionario;
import com.SGC.domain.TipoUsuario;

public class FuncionarioBusiness {
	private static FuncionarioBusiness instance;

	public static List<Funcionario> ObterLista() {
		List<Funcionario> Funcionarios = new ArrayList<Funcionario>();

		FuncionarioDAO userDAO = FuncionarioDAO.getInstance();
		Funcionarios = userDAO.getLista();

		return Funcionarios;
	}

	public static List<Funcionario> ObterLista(boolean ativos) {
		List<Funcionario> Funcionarios = new ArrayList<Funcionario>();

		FuncionarioDAO userDAO = FuncionarioDAO.getInstance();
		Funcionarios = userDAO.getLista(ativos);

		return Funcionarios;
	}

	public static Funcionario Obter(int id) {
		Funcionario Funcionario = new Funcionario();
		try {

			FuncionarioDAO useDAO = FuncionarioDAO.getInstance();
			Funcionario = useDAO.get(id);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return Funcionario;
	}

	public static Funcionario Obter(String Login, String Senha) {
		Funcionario Funcionario = new Funcionario();
		try {
			FuncionarioDAO useDAO = FuncionarioDAO.getInstance();
			String key = com.SGC.domain.Funcionario.Criptografar(Senha);
			Funcionario = useDAO.get(Login, key);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return Funcionario;
	}

	public static void Add(Funcionario u) {
		try {

			FuncionarioDAO useDAO = FuncionarioDAO.getInstance();
			useDAO.add(u);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void Update(Funcionario u) {
		try {

			FuncionarioDAO useDAO = FuncionarioDAO.getInstance();
			useDAO.Update(u);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static FuncionarioBusiness getInstance() {
		if (instance == null) {
			instance = new FuncionarioBusiness();
		}
		return instance;
	}

	public static Funcionario Converter(HttpServletRequest request) {

		Funcionario u = new Funcionario();

		if (request.getParameter("Id") != null
				|| request.getParameter("Id") != "")
			u.setId(Integer.parseInt(request.getParameter("Id")));

		String nome = (String) request.getParameter("Nome");
		u.setNome(nome);

		String RG = (String) request.getParameter("RG").replaceAll("\\D", "")
				.substring(0, 9);
		u.setRG(RG);

		String CPF = (String) request.getParameter("CPF").replaceAll("\\D", "")
				.substring(0, 11);
		u.setCPF(CPF);

		String Endereco = (String) request.getParameter("Endereco");
		u.setEndereco(Endereco);

		int Numero = Integer.parseInt((String) request.getParameter("Numero")
				.replaceAll("\\D", ""));
		u.setNumero(Numero);

		String Cidade = (String) request.getParameter("Cidade");
		u.setCidade(Cidade);

		String UF = (String) request.getParameter("UF");
		u.setUF(UF);

		String Bairro = (String) request.getParameter("Bairro");
		u.setBairro(Bairro);

		String CEP = (String) request.getParameter("CEP").replaceAll("\\D", "")
				.substring(0, 8);
		u.setCEP(CEP);

		String Email = (String) request.getParameter("Email");
		u.setEmail(Email);

		int tUsuario = Integer
				.parseInt((String) request.getParameter("Funcao"));

		TipoUsuario TipoUsuario = TipoUsuarioBusiness.Obter(tUsuario);

		u.setTipoUsuario(TipoUsuario);

		if(request.getParameter("password") != null || request.getParameter("password") != ""){
			String senha = (String) request.getParameter("password");
			u.setSenha(Funcionario.Criptografar(senha));
		}

		String Passe = request.getParameter("Passe");
		u.setPasse(Passe);

		boolean status = Boolean.parseBoolean((String) request
				.getParameter("status"));

		u.setStatus(status);

		return u;
	}

}
