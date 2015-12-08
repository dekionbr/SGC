package com.SGC.Test;

import java.util.List;

import com.SGC.business.*;
import com.SGC.domain.Funcionario;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Funcionario> funcionarios = FuncionarioBusiness.ObterLista();
		
		for(Funcionario user : funcionarios){
			System.out.println(user.getNome());
		}		
	}

}
