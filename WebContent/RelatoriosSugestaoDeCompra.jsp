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

		<br>
		<hr>
		<h3>Sugestão de Compra</h3>
		<br />
		<div>
			<% List<Relatorio> lista = (List<Relatorio>) request
											.getAttribute("sugestoes");
											
											%>

			<table>
				<thead>
					<tr>
						<td>Produto</td>
						<td>Quantidade Mínima</td>
						<td>Estoque Atual</td>
						<td>Sugestão de compra</td>
					</tr>
				</thead>
				<tbody>
					<% for (Relatorio rel : lista) {
							%>
					<tr>
						<th><%=rel.getLabel() %></th>
						<td><%=rel.getQuantidade() %></td>
						<td><%=rel.getColuna() %></td>
						<td><%=rel.getQuantidade() - rel.getColuna() %></td>
					</tr>

					<%} %>

					<!-- 					<tr> -->

					<!-- 						<th>Tilenol</th> -->
					<!-- 						<td>50</td> -->
					<!-- 						<td>30</td> -->
					<!-- 					</tr> -->
					<!-- 					<tr> -->

					<!-- 						<th>Tilenol</th> -->
					<!-- 						<td>50</td> -->
					<!-- 						<td>30</td> -->
					<!-- 					</tr> -->
				</tbody>
			</table>
		</div>


		<br /> <br />

	</div>

</section>

<script type="text/javascript">
	$(function() {
		var $form = $("form#form");

		$form.wl_Form({
			status : false,
			onBeforeSubmit : function(data) {
				$form.wl_Form('set', 'sent', false);
				$('.chart').empty();
				return true;
			},
			onError : function(textStatus, error, jqXHR) {
				$.msg(error, 'info', '#content');
				console.log(error);
				return false;
			},
			onSuccess : function(data, textStatus, jqXHR) {
				var obj = JSON.parse(data);
				if (obj != undefined && obj.data.length > 0) {
					$("#dvChart").wl_Chart(
							{
								type : "bars",
								xlabels : obj.xlabels,
								data : obj.data,
								tooltipPattern : function(value, legend, label,
										id) {
									return value
											+ " Ordens emitidas ao fornecedor "
											+ legend
											+ " no período: "
											+ label;
								}
							});
				} else {

					$("#dvChart").html("Dados não encontrato");
				}
			},
			onComplete : function() {
			}
		});

	});
</script>
<%@include file="include/footer.jsp"%>