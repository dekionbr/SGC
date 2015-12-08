<%@page import="org.apache.catalina.User"%>
<%@page import="java.util.List"%>
<%@page import="com.SGC.business.FornecedorBusiness"%>
<%@page import="com.SGC.domain.Fornecedor"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>


<section id="content">
	<div class="12">
		<h1>
			Fornecedores <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>

		<p>Cadastro de Fornecedor</p>

		<form>
			<fieldset>
				<section>
					<label></label>
					<div>
						<a href="Fornecedor" class="btn icon i_plus">Incluir</a>
						<%if(request.getParameter("ativo") == null && 
							 request.getParameter("ativo") != "false"){ %>
						<a href="Fornecedores?ativo=false"
							class="btn icon i_access_denied">Listar Inativos</a>
						<%} else { %>
						<a href="Fornecedores" class="btn icon i_tick">Listar Ativos</a>
						<%} %>
					</div>
				</section>
			</fieldset>
		</form>
	</div>

	<div class="g12">

		<p>Lista de Fornecedores cadastrados</p>

		<table class="datatable">
			<thead>
				<tr>
					<th>Razão Social</th>
					<th>Nome Fantasia</th>
					<th>CNPJ</th>
					<th>Representante</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%			
				
			 List<Fornecedor> fornecedores = (List<Fornecedor>) request.getAttribute("Fornecedores");
				
			 for(Fornecedor fornecedor : fornecedores){
			%>
				<tr
					class="<%= fornecedor.getStatus() ? "gradeA" : "gradeX" %> <%= (fornecedores.indexOf(fornecedor) % 2) == 0 ? "even": "add" %> ">
					<td><%=fornecedor.getRazaoSocial() %></td>
					<td><%=fornecedor.getNomeFantasia() %></td>
					<td><%=fornecedor.getCNPJ() %></td>
					<td class="c"><%=fornecedor.getNomeRepresentante() %></td>
					<td class="c"><a href="Fornecedor?f=<%=fornecedor.getId()%>">Editar</a>
						<%if(fornecedor.getStatus()) { %> <span> | </span> <a
						href="DesligarFornecedor?f=<%=fornecedor.getId()%>"
						class="desligar">Inativo</a> <%} %></td>
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
					
					$.confirm('Tem certeza que irá desativar esse fornecedor?',
							function() {
								$.ajax({
									type : 'POST',
									url : link.attr('href')
								}).done(function(e, status) {
									if (window.console) {
										console.log(status);
										console.log(e);
									};
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