package com.SGC.business;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.SGC.controller.EntradaServlet;
import com.SGC.dao.BaixaDAO;
import com.SGC.dao.EntradaDAO;
import com.SGC.dao.ItemBaixaDAO;
import com.SGC.domain.Baixa;
import com.SGC.domain.Entrada;
import com.SGC.domain.ItemBaixa;
import com.SGC.domain.Produto;

public class ItemBaixaBusiness {

	@SuppressWarnings("unchecked")
	public static List<ItemBaixa> ObterItemBaixaPorProduto(Produto produto) {
		List<ItemBaixa> baixas = null;
		try {
			ItemBaixaDAO Dao = new ItemBaixaDAO();
			Criteria crid = Dao.getSession().createCriteria(ItemBaixa.class);

			baixas = (List<ItemBaixa>) crid.add(
					Restrictions.eq("Produto.Id", produto.getId())).list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
		return baixas;
	}

	public static int ObterQtdProduto(int id) {
		int entradas = 0;
		int baixas = 0;
		try {
			ItemBaixaDAO baixaSao = ItemBaixaDAO.getInstance();
			Criteria cridBaixa = baixaSao.getSession().createCriteria(
					ItemBaixa.class);

			EntradaDAO entradaDao = EntradaDAO.getInstance();
			Criteria entrBaixa = entradaDao.getSession().createCriteria(
					Entrada.class);
			try {

				entradas = ((Number) entrBaixa
						.add(Restrictions.eq("Produto.Id", id))
						.setProjection(Projections.sum("QtdLote"))
						.uniqueResult()).intValue();
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				baixas = ((Number) cridBaixa
						.add(Restrictions.eq("Produto.Id", id))
						.setProjection(Projections.sum("Quantidade"))
						.uniqueResult()).intValue();
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return entradas - baixas;
	}
}
