package com.SGC.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.SGC.dao.ProdutoDAO;
import com.SGC.domain.Baixa;
import com.SGC.domain.Entrada;
import com.SGC.domain.ItemBaixa;
import com.SGC.domain.Produto;
import com.SGC.domain.TipoProduto;
import com.SGC.extend.Conteiner;
import com.SGC.extend.HashConteiner;

public class ProdutoBusiness {
	private static ProdutoBusiness instance;

	// public Map<TipoProduto, List<Produto>> ObterListaMap(boolean ativo) {
	// Map<TipoProduto, List<Produto>> map = new HashMap<TipoProduto,
	// List<Produto>>();
	//
	// ProdutoDAO proDao = ProdutoDAO.getInstance();
	// List<Produto> Produtos = proDao.se
	//
	// for (Iterator<Produto> iterator = Produtos.iterator();
	// iterator.hasNext();) {
	// Produto produto = (Produto) iterator.next();
	//
	// }
	//
	// return map;
	// }

	public static List<Produto> ObterLista() {
		List<Produto> Produtos = new ArrayList<Produto>();
		try {
			ProdutoDAO proDao = ProdutoDAO.getInstance();
			Produtos = proDao.getSession().createCriteria(Produto.class)
					.addOrder(Order.asc("Nome")).list();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return Produtos;
	}

	public static Produto Obter(int id) {
		Produto produto = null;
		try {
			ProdutoDAO proDao = ProdutoDAO.getInstance();
			produto = proDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}

	public static void Add(Produto Produto) {
		try {
			ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
			produtoDAO.add(Produto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Update(Produto produto) {
		try {

			ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
			produtoDAO.Update(produto);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Finalizar o relatório.
	public static Map<String, Integer> GerarRelatorio(Date Init, Date Fim,
			int corte, int[] IdProdutos) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		return map;
	}

	public static Map<String, Integer> GerarRelatorio(Date Init, Date Fim,
			int corte) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		try {

			ProdutoDAO produtoDAO = ProdutoDAO.getInstance();

			Criteria crid = produtoDAO.getSession().createCriteria(
					Produto.class);

			// crid.add("")

			List<Produto> produtos = crid.list();

			Iterator<Produto> en = produtos.iterator();

			while (en.hasNext()) {
				Produto prod = (Produto) en.next();
				map.put(prod.getNome(), 1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return map;
	}

	public static Produto Converter(HttpServletRequest request) {
		Produto produto = new Produto();

		if (request.getParameter("Id") != null
				|| request.getParameter("Id") != "")
			produto.setId(Integer.parseInt(request.getParameter("Id")));

		String Nome = request.getParameter("nome");
		produto.setNome(Nome);

		String Descricao = request.getParameter("descricao");
		produto.setDescricao(Descricao);

		int QtdMinima = Integer.parseInt(request.getParameter("qtdminima"));
		produto.setQtdMinima(QtdMinima);

		int TProduto = Integer.parseInt(request.getParameter("tipoproduto"));
		produto.setTipoProduto(TipoProduto.values()[TProduto]);

		boolean status = Boolean.parseBoolean((String) request
				.getParameter("status"));

		produto.setStatus(status);

		return produto;
	}

	public static List<Produto> ObterLista(boolean ativos) {
		List<Produto> Produtos = new ArrayList<Produto>();

		try {

			ProdutoDAO proDao = ProdutoDAO.getInstance();
			Produtos = proDao.getLista(ativos);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Produtos;
	}

	public static ProdutoBusiness getInstance() {
		if (instance == null) {
			instance = new ProdutoBusiness();
		}
		return instance;
	}

	public static long Estoque(Produto produto) {

		Conteiner<Entrada> entradas = new HashConteiner<Entrada>(
				EntradaBusiness.ObterEntradasPorProduto(produto));

		Conteiner<ItemBaixa> baixas = new HashConteiner<ItemBaixa>(
				ItemBaixaBusiness.ObterItemBaixaPorProduto(produto));

		return Math.round(entradas.Sum("QtdLote"))
				- Math.round(baixas.Sum("Quantidade"));
	}
}
