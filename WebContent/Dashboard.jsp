<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>


<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>

<section id="content">

	<div class="g12 nodrop">
		<h1>Dashboard</h1>
		<p>Central de Gestão de produtos</p>
	</div>


	<div class="g6 widgets">

		<div class="widget number-widget" id="widget_number">
			<h3 class="handle">Numeros</h3>
			<div>
				<ul>
					<li><a href=""><span><%=request.getAttribute("TotalMedicamento")%></span>
							Total de medicamentos em estoque </a></li>
					<li><a href=""><span><%=request.getAttribute("TotalMaterial")%></span>
							Total de material em estoque</a></li>
					<li><a href=""><span><%=request.getAttribute("AVencer")%></span>
							Produtos a vencer nos próximos 10 dias </a></li>
					<li><a href=""><span><%=request.getAttribute("PedidosHoje")%></span>
							Pedidos de produtos hoje</a></li>
					<li><a href=""><span><%=request.getAttribute("SetoresAtendidos")%></span>
							Departamentos Atendidos hoje</a></li>
					<li><a href=""><span><%=request.getAttribute("TotalFornecedores")%></span>
							Total de Fornecedores</a></li>
				</ul>
			</div>
		</div>
	</div>	

</section>

<%@include file="include/footer.jsp"%>
