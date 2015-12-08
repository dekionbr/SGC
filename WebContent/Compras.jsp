<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>


<section id="content">



	<div class="g12">
		<h1>Pedidos</h1>

		<table class="datatable">
			<thead>
				<tr>
					<th>Pedido</th>
					<th>Data do pedido</th>
					<th>Quantidade do Produto</th>
					<th>EmergÃªncia</th>
				</tr>
			</thead>
			<tbody>
				<tr class="gradeX">
					<td>Dipirona</td>
					<td>01/11/2015 08:30</td>
					<td>100</td>
					<td class="c">Alta</td>
				</tr>
				<tr class="gradeA">
					<td>Seringas</td>
					<td>01/11/2015 09:30</td>
					<td>200</td>
					<td class="c">Baixa</td>
				</tr>
				<tr class="gradeA">
					<td>Vassouras</td>
					<td>20/06/2015 11:30</td>
					<td>5</td>
					<td class="c">Baixa</td>
				</tr>
				<tr class="gradeA">
					<td>Gaze</td>
					<td>01/06/2015 13:30</td>
					<td>200</td>
					<td class="c">Baixa</td>
				</tr>
			</tbody>
		</table>
	</div>
	<form id="form" action="" method="post" autocomplete="off">
		<fieldset>
			<label>Cadastro de pedido</label>
			<section>
				<label for="autocomplete_inline">Pedido</label>
				<div>
					<input id="autocomplete_inline" name="autocomplete_inline"
						type="text" class="autocomplete"
						data-source="[Detergente,Dipirona,Gaze,Metamizol,Novalgina,Seringas,Seringas tamnho 1,Seringas tamnho 2,Vassouras]">
				</div>
			</section>
			<section>
				<label for="datapedido">Data do pedido</label>
				<div>
					<input id="datapedido" name="datapedido" type="text" class="date">
				</div>
			</section>
			<section>
				<label for="qtdproduto">Quantidade do produto</label>
				<div>
					<input type="integer" id="qtdproduto" name="qtdproduto"
						class="integer">
				</div>
			</section>
			<section>
				<label for="emergencia">EmergÃªncia</label>
				<div>
					<select name="emergencia" id="emergencia">
						<option value="0">Baixa</option>
						<option value="1">Alta</option>
					</select>
				</div>
			</section>
		</fieldset>
		<fieldset>
			<section>
				<div>
					<button class="reset">Limpar</button>
					<button class="submit" name="Cadastrar" value="Cadastrar">Cadastrar</button>
				</div>
			</section>
		</fieldset>
	</form>

</section>

<%@include file="include/footer.jsp"%>