<%@page import="com.SGC.domain.Ordem"%>
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
			Entrada de Compra <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>
		<p>Painel de cadastros de produto</p>
		<form action="IncluirEntrada" method="post" autocomplete="off">
			<fieldset>
				<label>Dados de Entrada</label>
				<!-- 				<section> -->
				<!-- 					<label for="nome">Produto</label> -->
				<!-- 					<div> -->
				<!-- 						<select name="Produto" id="produto" required="required"> -->

				<!-- 								<option value="">Selecione o produto</option> -->
				<%-- 								<% 
				// List
				<Produto> produtos = (List<Produto>) request //
				.getAttribute("Produtos"); // for (Produto produto : produtos) { 								%> --%>
				<%-- 								<option value="<%=produto.getId()%>"><%=produto.getNome()%></option> --%>
				<%-- 								<%  // }  								%> --%>
				<!-- 							</select> -->
				<!-- 					</div> -->
				<!-- 				</section> -->
				<!-- 				<section> -->
				<!-- 					<label for="Lote">Número do lote</label> -->
				<!-- 					<div> -->
				<!-- 						<input type="text" required="required" id="Lote" -->
				<!-- 							name="Lote"> -->
				<!-- 					</div> -->
				<!-- 				</section> -->
				<!-- 				<section> -->
				<!-- 					<label for="qtdLote">Quantidade do lote</label> -->
				<!-- 					<div> -->
				<!-- 						<input type="text" required="required" id="qtdLote" -->
				<!-- 							name="qtdLote"> -->
				<!-- 					</div> -->
				<!-- 				</section> -->
				<!-- 				<section> -->
				<!-- 					<label for="Validade">Validade</label> -->
				<!-- 					<div> -->
				<!-- 						<input type="text" required="required" class="date datetime" id="Validade" -->
				<!-- 							name="Validade"> -->
				<!-- 					</div> -->
				<!-- 				</section> -->
				<!-- 				<section> -->
				<!-- 					<label for="Valor">Valor</label> -->
				<!-- 					<div> -->
				<!-- 						<input type="text" required="required" id="Valor" -->
				<!-- 							name="Valor" style="width: 100px;"> -->
				<!-- 					</div> -->
				<!-- 				</section> -->
				<section>
					<label for="Ordem">Ordem</label>
					<div>
						<select name="Ordem" id="Ordem" required="required"
							class="cbAutocomplite">
							<option value="">Selecione a Ordem</option>
							<%
									List<Ordem> Ordens = (List<Ordem>) request
											.getAttribute("Ordens");

									for (Ordem ordem : Ordens) {
								%>
							<option value="<%=ordem.getId()%>"><%=ordem.getFornecedor().getNomeFantasia()%>
								- (<%=ordem.ObterDataEntrega()%>)
							</option>
							<%
									}
								%>
						</select>
					</div>
				</section>
				<section>
					<label for="Fecha">Fechar Ordem?</label>
					<div>
						<input type="radio" id="sim" name="Fecha" checked="checked"
							value="true"><label for="sim">Sim</label> <input
							type="radio" id="nao" name="Fecha" value="false"><label
							for="nao">Não</label>
					</div>
				</section>
			</fieldset>
			<fieldset>
				<label>Lista produtos</label>
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
					<table id="ptTable">
						<thead>
							<tr>
								<td>Produto</td>
								<td>Lote</td>
								<td>Quantidade</td>
								<td>Validade</td>
								<td>Valor</td>
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
	$(function(){
		var $select = $('#Ordem');
		var $body = $("#ptTable tbody");
		var $prodSelect = $("#produtos");
		var rows = 0;
		
		$select.on("change",
				function(e) {
					e.preventDefault();
					
					
							var data = {
								Id : $select.val()
							}

								$.ajax({
									type : 'POST',
									url : "ObterItensOrdem",
									data : data
								}).done(function(e, status) {									
									if (window.console) {
										console.log(status);
										console.log(e);
									}
									;
									var obj = jQuery.parseJSON(e);
									
									for ( var i = 0; i < obj.length; i++) {
										var row = "<tr>"
						 				+ "<th>"+obj[i].Produto+" <input type=\"hidden\" value=\""+obj[i].Id+"\" id=\"Produto_"+rows+"\""
					 					+ "	name=\"Produto."+rows+"\">"
					 					+ "<input type=\"hidden\" value=\""+obj[i].Ordem+"\" id=\"ItemOrdem_"+rows+"\""
					 					+ "	name=\"ItemOrdem."+rows+"\">"
					 					+ "</th>"
					 					+ "<td><input type=\"text\" id=\"Lote_"+rows+"\""
					 					+ "	name=\"Lote."+rows+"\" style=\"width: 200px;\"></td>"
					 					+ "<td><input type=\"number\" class=\"integer\" id=\"qtdLote_"+rows+"\""
					 					+ "	name=\"qtdLote."+rows+"\" value=\""+obj[i].Qtd+"\" style=\"width: 200px;\"></td>"
					 					+ "<td><input type=\"text\" class=\"date datetime\" id=\"Validade_"+rows+"\" name=\"Validade."+rows+"\"></td>"
					 					+ "<td><input type=\"number\" required=\"required\" class=\"decimal\" id=\"Valor_"+rows+"\""
					 					+ "	name=\"Valor."+rows+"\" style=\"width: 100px;\"></td>"
					 					+ "<td><a class=\"excluir\">Excluir</a></td>"
					 					+ "</tr>";
					 					
					 					$body.append(row);
					 					$("#Validade_"+rows).not(".hasDatepicker").wl_Date({
											dateFormat: 'dd-mm-yy'
										});
					 										 					
					 					rows++;
									}
									
									$body.find("input[type=number].integer").wl_Number();
									$body.find("input[type=number].decimal").wl_Number({
										decimals : 2,
										step : 0.5
									});
				 					
								});
				});
		
		$prodSelect
		.on(
				"change",
				function(e) {
					rows++;
					var produto = $prodSelect.find("option:selected").text(),
						idProduto = $prodSelect.val();
							
							var row = "<tr>"
				 				+ "<th>"+produto+" <input type=\"hidden\" value=\""+idProduto+"\" id=\"Produto_"+rows+"\""
			 					+ "	name=\"Produto."+rows+"\">"
			 					+ "<input type=\"hidden\" value=\"0\" id=\"ItemOrdem_"+rows+"\""
			 					+ "	name=\"ItemOrdem."+rows+"\">"
			 					+ "</th>"
			 					+ "<td><input type=\"text\" id=\"Lote_"+rows+"\""
			 					+ "	name=\"Lote."+rows+"\" style=\"width: 200px;\"></td>"
			 					+ "<td><input type=\"number\" class=\"integer\" id=\"qtdLote_"+rows+"\""
			 					+ "	name=\"qtdLote."+rows+"\" value=\"0\" style=\"width: 200px;\"></td>"
			 					+ "<td><input type=\"text\" class=\"date datetime\" id=\"Validade_"+rows+"\" name=\"Validade."+rows+"\"></td>"
			 					+ "<td><input type=\"number\" required=\"required\" classe=\"decimal\" id=\"Valor_"+rows+"\""
			 					+ "	name=\"Valor."+rows+"\" style=\"width: 100px;\"></td>"
			 					+ "<td><a class=\"excluir\">Excluir</a></td>"
			 					+ "</tr>";

							$body.append(row);
							$body.find("input[type=number].integer").wl_Number();
		 					$body.find('input.datetime').not(".hasDatepicker").wl_Date({
								dateFormat: 'dd-mm-yy'
							});
		 					$body.find("input[type=number].decimal").wl_Number({
								decimals : 2,
								step : 0.5
							});
					$("#qtd_" + idProduto).wl_Number();

				});
		
		$body.on("click", ".excluir", function(e) {
			e.preventDefault();
			var $row = $(this).parent().parent();
			$row.remove();
		});
	});
</script>

<%@include file="include/footer.jsp"%>