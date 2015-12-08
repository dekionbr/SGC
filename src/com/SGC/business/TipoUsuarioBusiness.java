package com.SGC.business;

import java.util.List;

import com.SGC.dao.TipoUsuarioDAO;
import com.SGC.domain.TipoUsuario;

public class TipoUsuarioBusiness {
	
	public static List<TipoUsuario> ObterLista(){		
		List<TipoUsuario> tipoUsuario = null;
		try {
			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();			
			tipoUsuario = tipoUsuarioDAO.getLista();
			
		} catch (Exception e) {

			e.printStackTrace();
		}	
		
		return tipoUsuario;
	}
	
	public static TipoUsuario Obter(int id){		
		TipoUsuario tipoUsuario = null;
		try {
			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
			tipoUsuario = tipoUsuarioDAO.get(id);
			
		} catch (Exception e) {

			e.printStackTrace();
		}	
		
		return tipoUsuario;
	}

	public static void Add(TipoUsuario tipoUsuario) {
		try {
			
			TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
			tipoUsuarioDAO.add(tipoUsuario);
			
		} catch (Exception e) {

			e.printStackTrace();
		}	
		
	}
}
