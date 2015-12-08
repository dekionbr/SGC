<%@page import="com.SGC.business.ItemBaixaBusiness"%>
<%@page import="com.SGC.business.BaixaBusiness"%>
<%@page import="com.SGC.business.EntradaBusiness"%>
<%@page import="java.util.List"%>
<%@page import="com.SGC.business.ProdutoBusiness"%>
<%@page import="com.SGC.domain.Produto"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>


<section id="content">
	<div class="12">
		<h1>
			Produtos <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>

		<p>Cadastro de Produtos</p>

		<form>
			<fieldset>
				<section>
					<label></label>
					<div>
						<a href="Produto" class="btn icon i_plus">Incluir</a>
						<%
							if (request.getParameter("ativo") == null
									&& request.getParameter("ativo") != "false") {
						%>
						<a href="Produtos?ativo=false" class="btn icon i_access_denied">Listar
							Inativos</a>
						<%
							} else {
						%>
						<a href="Produtos" class="btn icon i_tick">Listar Ativos</a>
						<%
							}
						%>
					</div>
				</section>
			</fieldset>
		</form>
	</div>

	<div class="g12">

		<p>Lista de produtos cadastrados</p>

		<table class="datatable">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Descricao</th>
					<th>Qtd Minima</th>
					<th>Qtd Atual</th>
					<th>Finalidade</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					List<Produto> produtos = (List<Produto>) request
							.getAttribute("Produtos");

					for (Produto produto : produtos) {
						int qtdAtual = ItemBaixaBusiness.ObterQtdProduto(produto.getId());
				%>
				<tr
					class="<%=produto.getStatus() ? "gradeA" : "gradeX"%>  <%=(produtos.indexOf(produto) % 2) == 0 ? "even" : "add"%>">
					<td><%=produto.getNome()%></td>
					<td><%=produto.getDescricao()%></td>
					<td><%=produto.getQtdMinima()%></td>
					<td><%=qtdAtual%></td>
					<td class="c"><%=produto.getTipoProduto().toString()%></td>
					<td class="c"><a href="Produto?p=<%=produto.getId()%>">Editar</a>
						<%
							if (produto.getStatus()) {
						%> <span> | </span> <a
						href="DesativarProduto?p=<%=produto.getId()%>" class="desligar">Inativo</a>
						<%
					 	}
					 %></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

</section>

<script type="text/javascript">
<!--
	$(function() {
		$('.desligar').click(
				function(e) {
					e.preventDefault();

					var link = $(this);

					$.confirm('Tem certeza que irá desativar esse Produto?',
							function() {
								$.ajax({
									type : 'POST',
									url : link.attr('href')
								}).done(function(e, status) {
									if (window.console) {
										console.log(status);
										console.log(e);
									}
									;
									var obj = jQuery.parseJSON(e);
									if (obj.sucess) {
										var m = $.msg(obj.msg, {
											sticky : true,
											header : 'Mensagem'
										});

										setTimeout(function() {
											if (m)
												m.close(function() {
													window.location = obj.url;
												});
										}, 6000);
									} else {
										$.msg(obj.msg, {
											header : 'Alerta'
										});
									}
								});
							});
				});
	});
//-->
</script>

<%@include file="include/footer.jsp"%>