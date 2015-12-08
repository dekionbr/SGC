<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.SGC.business.FuncionarioBusiness"%>
<%@page import="com.SGC.domain.Funcionario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>


<section id="content">
	<div class="12">
		<h1>
			Funcionários <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>

		<p>Cadastro de funcionários</p>

		<form>
			<fieldset>
				<section>
					<label></label>
					<div>
						<a href="Funcionario" class="btn icon i_plus">Incluir</a>
						<%if(request.getParameter("ativo") == null && 
							 request.getParameter("ativo") != "false"){ %>
						<a href="Funcionarios?ativo=false"
							class="btn icon i_access_denied">Listar Inativos</a>
						<%} else { %>
						<a href="Funcionarios" class="btn icon i_tick">Listar Ativos</a>
						<%} %>
					</div>
				</section>
			</fieldset>
		</form>
	</div>

	<div class="g12">

		<p>Lista de funcionários cadastrados</p>

		<table class="datatable">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Usuario</th>
					<th>CPF</th>
					<th>Função</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					List<Funcionario> Funcionarios = (List<Funcionario>) request.getAttribute("Funcionarios");
					for (Funcionario funcionario : Funcionarios) {
				%>
				<tr
					class="<%=funcionario.getStatus() ? "gradeA" : "gradeX"%> <%=(Funcionarios.indexOf(funcionario) % 2) == 0 ? "even" : "add"%>">
					<td><%=funcionario.getNome()%></td>
					<td><%=funcionario.getPasse()%></td>
					<td><%=funcionario.getCPF()%></td>
					<td class="c"><%=funcionario.getTipoUsuario().getValor()%></td>
					<td class="c"><a
						href="Funcionario?id=<%=funcionario.getId()%>">Editar</a> <%
							if (funcionario.getStatus()) {
						%> <span> | </span> <a
						href="DesativarFuncionario?id=<%=funcionario.getId()%>"
						class="desligar">Inativo</a></td>
					<%
						}
					%>
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
					
					$.confirm('Tem certeza que irá desativar o funcionário?',
							function() {
								$.ajax({
									url : link.attr('href'),
									type : 'POST'
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