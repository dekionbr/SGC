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
			Relatórios <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>
		<p>Painel de relatórios gerenciais</p>
		<form id="form" action="GerarRelatoriosFornecedor" method="post"
			autocomplete="off">
			<fieldset>
				<label>Relatório Fornecedor</label>
				<section>
					<label for="Periodo">Períodos</label>
					<div>
						<span>De:</span> <input name="Init" type="text" class="date de" />
						<span>a:</span> <input name="Fim" type="text" class="date ate" />
					</div>
				</section>
				<section>
					<label for="comparativo">Corte</label>
					<div>
						<select id="Corte" name="Corte" required="required">
							<option value="">Selecione o Corte</option>
							<option value="0">Diario</option>
							<option value="1">Semanal</option>
							<option value="2">Mensal</option>
							<option value="3">Trimestral</option>
							<option value="5">Semestral</option>
							<option value="4">Anual</option>
						</select>
					</div>
				</section>
				<section>
					<label for="comparativo">Produtos</label>
					<div>
						<select name="Fornecedores" id="Fornecedores" multiple="multiple">
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
					<div>
						<button class="submit">Enviar</button>
					</div>
				</section>
			</fieldset>
		</form>
		<br>

		<hr>
		<h3>Fornecedor</h3>

		<div id="dvChart">
			<canvas id="canvas" height="350" width="600"></canvas>
		</div>

		<br /> <br />
	</div>

</section>

<script type="text/javascript">
	$(function() {
		var $form = $("form#form");
		var $chart = $('#dvChart');

		$form.wl_Form({
			status : false,
			onBeforeSubmit : function(data) {
				$form.wl_Form('set', 'sent', false);
// 				$('.chart').empty();
				return true;
			},
			onError : function(textStatus, error, jqXHR) {
				$.msg(error, 'info', '#content');
				console.log(error);
				return false;
			},
			onSuccess : function(data, textStatus, jqXHR) {
				var obj = JSON.parse(data);
				if (obj != undefined) {
					
					window.getChart(obj, "canvas");
				} else {
					
					$chart.html("Dados não encontrato");
				}
			},
			onComplete : function() {
			}
		});

	});
</script>

<%@include file="include/footer.jsp"%>