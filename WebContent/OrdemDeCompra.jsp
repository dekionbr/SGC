<%@page import="com.SGC.domain.*"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>

<section id="content">
	<div class="g12">
		<h1>
			Ordem de Compra <span><a href="ajuda.jsp" class="small">Ajuda</a></span>
		</h1>
		<p>Painel de entrade de ordem de compra</p>

		<form action="IncluirOrdemDeCompra" method="post" autocomplete="off">
			<fieldset>
				<label>Dados da Ordem de Compra</label>
				<section>
					<label for="Fornecedor">Fornecedor</label>
					<div>
						<select name="Fornecedor" id="Fornecedor" required="required"
							class="cbAutocomplite">
							<option value="0">Selecione o Fornecedor</option>
							<%
								List<Fornecedor> Fornecedores = (List<Fornecedor>) request
										.getAttribute("Fornecedores");

								for (Fornecedor fornecedor : Fornecedores) {
							%>
							<option value="<%=fornecedor.getId()%>"><%=fornecedor.getNomeFantasia()%></option>
							<%
								}
							%>
						</select>

					</div>
				</section>
				<section>
					<label for="Nome">Data de Entrega</label>
					<div>
						<input type="text" id="dataEntrega" name="dataEntrega"
							class="datetime date">
					</div>
				</section>
				<section>
					<label for="Normal">Prioridade</label>
					<div>
						<input type="radio" id="Alta" name="Prioridade" value="0"
							required="required"><label for="Alta">Alta</label> <input
							type="radio" id="Normal" name="Prioridade" value="1"
							required="required" checked="checked"><label for="Normal">Normal</label>
					</div>
				</section>
				<section>
					<label for="Nome">Justificativa</label>
					<div>
						<textarea id="textarea" name="justificativa" rows="6"></textarea>
					</div>
				</section>
			</fieldset>
			<fieldset>
				<label>Itens de compra</label>
				<section>
					<label for="Nome">Produtos</label>
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
					<table id="ItensOrdens">
						<thead>
							<tr>
								<td>Produto</td>
								<td>Quantidade</td>
								<td></td>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</section>
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
		var $eventSelect = $("#produtos"), $ordens = $('#ItensOrdens');

		$eventSelect
				.on(
						"change",
						function(e) {

							var row = "<tr>"
									+ "<th>"
									+ $eventSelect.find("option:selected")
											.text()
									+ " <input type=\"hidden\" id=\"Produto_"
									+ $eventSelect.val() + "\" name=\"Produto."
									+ $eventSelect.val() + "\" value=\""
									+ $eventSelect.val()
									+ "\" />"
									+ "</th>"
									+ "<td><input type=\"number\" class=\"integer\" data-min=\"0\" id=\"qtd_"
									+ $eventSelect.val()
									+ "\" name=\"qtd_"
									+ $eventSelect.val()
									+ "\" value=\"0\"></td>"
									+ "<td><a class=\"excluir\">Excluir</a></td>"
									+ "</tr>";

							$ordens.find("tbody").append(row);
							$("#qtd_" + $eventSelect.val()).wl_Number();

						});

		$ordens.on("click", ".excluir", function() {			
			var $row = $(this).parent().parent();
			$row.remove();
		});

	});
</script>

<%@include file="include/footer.jsp"%>