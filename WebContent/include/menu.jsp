<%@page import="com.SGC.business.TipoUsuarioBusiness"%>
<%@page import="com.SGC.domain.TipoUsuario"%>
<%@page import="com.SGC.domain.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%
	Funcionario user = (Funcionario) session.getAttribute("usuario");
	TipoUsuario tu = user.getTipoUsuario();
%>

<nav>
	<ul id="nav">
		<li class="i_house"><a href="Dashboard"><span>Dashboard</span></a></li>
		<%
			if (tu.getValor().equals("Gestor")
					|| tu.getValor().equals("Administrador")) {
		%>
		<li class="i_book"><a><span>Cadastros</span></a>
			<ul>

				<li><a href="Funcionarios"><span>Funcionários</span></a></li>
				<li><a href="Fornecedores"><span>Fornecedores</span></a></li>
				<li><a href="Produtos"><span>Produtos</span></a></li>

			</ul></li>
		<%
			}
		%>
		<%
			if (tu.getValor().equals("Atendente")
					|| tu.getValor().equals("Administrador")) {
		%>
		<li class="i_truck"><a href="Baixa"><span>Baixa
					Estoque</span></a></li>
		<%
			}
		%>

		<%
			if (tu.getValor().equals("Comprador")
					|| tu.getValor().equals("Administrador")) {
		%>
		<li class="i_money"><a><span>Compras</span></a>
			<ul>
				<li><a href="OrdemDeCompra"><span>Ordem</span></a></li>
				<li><a href="Entrada"><span>Entrada</span></a></li>
			</ul></li>
		<%
			}
		%>
		<li class="i_graph"><a><span>Relatórios</span></a>
			<ul>
				<%
					if (tu.getValor().equals("Comprador")
							|| tu.getValor().equals("Administrador")) {
				%>
				<li><a href="RelatoriosSugestaoDeCompra"><span>Sugestão
							de Compra</span></a></li>
				<li><a href="RelatoriosOrdemDeCompra"><span>Ordem de
							Compra</span></a></li>
				<li><a href="RelatoriosEntrada"><span>Entrada</span></a></li>
				<%
					}
				%>
				<%
					if (tu.getValor().equals("Gestor")
							|| tu.getValor().equals("Administrador")) {
				%>
				<li><a href="RelatoriosComparativo"><span>Comparativo</span></a></li>
				<li><a href="RelatoriosBaixa"><span>Baixas Por Setor</span></a></li>
				<li><a href="RelatoriosValidade"><span>Validade a
							Vencer</span></a></li>
				<li><a href="RelatoriosFornecedor"><span>Fornecedor</span></a></li>
				<%
					}
				%>
				<%
					if (tu.getValor().equals("Atendente")
							|| tu.getValor().equals("Administrador")) {
				%>
				<li><a href="RelatoriosPerdas"><span>Perdas</span></a></li>
				<%
					}
				%>
			</ul></li>
	</ul>
</nav>