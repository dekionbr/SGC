<%@page import="com.SGC.domain.MotivoBaixa"%>
<%@page import="com.SGC.business.ProdutoBusiness"%>
<%@page import="com.SGC.domain.Produto"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>

<section id="content">
	<div class="g12">
		<h1>
			Cadastro de Baixa <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>
		<p>Painel de cadastros de baixa de estoque</p>

		<form action="IncluirBaixa" method="post" autocomplete="off">
			<fieldset>
				<label>Dados da Baixa</label>
				<section>
					<label for="Nome">Nome do solicitante</label>
					<div>
						<input type="text" id="Nome" name="Nome" required="required" />
					</div>
				</section>
				<section>
					<label for="Matricula">Matrícula</label>
					<div>
						<input type="text" id="Matricula" name="Matricula"
							required="required" />
					</div>
				</section>
				<section>
					<label for="tipo">Setor</label>
					<div>
						<input type="text" id="Setor" name="Setor" required="required" />
					</div>
				</section>
				<section>
					<label for="Motivo">Motivos da baixa</label>
					<div>
						<select name="Motivo" id="Motivo" required="required">
							<option value="">Selecione o Motivo</option>
							<%
								MotivoBaixa[] motivos = MotivoBaixa.values();

								for (MotivoBaixa motivo : motivos) {
							%>
							<option value="<%=motivo.ordinal()%>"><%=motivo.toString()%></option>
							<%
								}
							%>
						</select>
					</div>
				</section>
				<fieldset>
					<label>Itens da baixa</label>
					<section>
						<label for="produtos"> </label>
						<div>
							<select name="produtos" id="produtos" class="cbAutocomplite">

								<option value="">Selecione o produto</option>
								<%
									List<Produto> produtos = (List<Produto>) request
											.getAttribute("Produtos");

									for (Produto produto : produtos) {
								%>
								<option value="<%=produto.getId()%>"><%=produto.getNome()%></option>
								<%
									}
								%>
							</select>
						</div>
					</section>
					<section>
						<table id="itensBaixa">
							<thead>
								<tr>
									<td>Produto</td>
									<td>Validade</td>
									<td>Quantidade</td>
									<td></td>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</section>
				</fieldset>
			</fieldset>
			<fieldset>
				<section>
					<div>
						<button class="submit" name="Cadastrar" value="Cadastrar">Cadastrar</button>
						<button class="reset">Limpar</button>
					</div>
				</section>
			</fieldset>
		</form>
	</div>

</section>

<script>
	$(function() {
		var $eventSelect = $("#produtos"), $baixas = $('#itensBaixa');

		$eventSelect
				.on(
						"change",
						function(e) {

							var data = {
								Id : $eventSelect.val()
							}
							$
									.ajax({
										url : "ObterEntradas",
										type : 'POST',
										data : data
									})
									.done(
											function(e, status) {

												if (window.console) {
													console.log(status);
													console.log(e);
												}
												;

												var obj = jQuery.parseJSON(e);

												var row = "<tr>"
														+ "<th>"
														+ obj.Produto
														+ "</th>"
														+ "<td>"
														+ "<select name=\"Entrada."
														+ obj.Id
														+ "\" id=\"Entrada_"
														+ obj.Id
														+ "\" class=\"Entrada\" data-produto=\""+ obj.Id+"\">"
														+ "		<option value=\"\">Selecione a validade</option>";

												for ( var int = 0; int < obj.Entradas.length; int++) {
													row += "		<option value=\""+obj.Entradas[int].Id+"\" data-qtd=\""+obj.Entradas[int].Qtd+"\">"
															+ obj.Entradas[int].Validade
															+ "</option>";
												}

												row += "</select>"
														+ "</td>"
														+ "<td>"
														+ "<span id=\"qtd_"+obj.Id+"\"></span>"
														+ "</td>"
														+ "<td><a class=\"excluir\" href=\"#\">Excluir</a></td>"
														+ "</tr>";
														
												$baixas.find("tbody")
														.append (row);

												var $select = $("#Entrada_"
														+ obj.Id);
												
												$select.uniform();

												$select
														.on(
																"change",
																function() {
																	var idProduto = $(this).data("produto"), qtd = $(this).find("option:selected").data("qtd");
																$("#qtd_"+idProduto).html("<input type=\"number\" class=\"integer\" id=\"Quantidade_" + idProduto + "\""
												    + "	name=\"Quantidade." + idProduto + "\" value=\"0\" data-min=\"0\" data-max=\""+qtd+"\"> <span> : </span>"
																			+ "<span>"
																			+ qtd
																			+ "</span>");
																			$("#qtd_"+idProduto+" .integer").wl_Number();
																});

											});
						});

		$baixas.on("click", ".excluir", function() {
			var $row = $(this).parent().parent();
			$row.remove();
		});

	});
</script>
<%@include file="include/footer.jsp"%>