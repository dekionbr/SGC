<%@page import="java.util.List"%>
<%@page import="com.SGC.business.*"%>
<%@page import="com.SGC.domain.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%
	Produto produto = (Produto) request.getAttribute("Produto");
%>


<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>

<section id="content">

	<div class="g12">
		<h1>
			Cadastro de Produto <span><a href="ajuda.html" class="small">Ajuda</a></span>
		</h1>
		<p>Painel de cadastros de produto</p>


		<form action="IncluirProduto" method="post" autocomplete="off">
			<input type="hidden" id="Id" name="Id" value="<%=produto.getId()%>">
			<fieldset>
				<label>Dados Produto</label>
				<section>
					<label for="nome">Nome</label>
					<div>
						<input type="text" required="required" id="nome" name="nome"
							value="<%=produto.getNome()%>">
					</div>
				</section>
				<section>
					<label for="descricao">Descrição</label>
					<div>
						<input type="text" required="required" id="descricao"
							name="descricao" value="<%=produto.getDescricao()%>">
					</div>
				</section>
				<section>
					<label for="qtdminima">Quantidade mínima</label>
					<div>
						<input type="number" required="required" id="qtdminima"
							name="qtdminima" title="Quantidade minima do lote cadastrado"
							class="integer" value="<%=produto.getQtdMinima()%>">
					</div>
				</section>
				<section>
					<label for="tipoproduto">Tipo de produto</label>
					<div>
						<select name="tipoproduto" id="tipoproduto">
							<option value="">Selecione o tipo do produto</option>
							<%
								TipoProduto[] tipos = TipoProduto.values();
								for (TipoProduto tProduto : tipos) {
									String selected = tProduto == produto.getTipoProduto() ? "selected=\"selected\""
											: "";
							%>
							<option value="<%=tProduto.ordinal()%>" <%=selected%>><%=tProduto.toString()%></option>
							<%
								}
							%>
						</select>
					</div>
				</section>
				<section>
					<label>Situação</label>
					<div>
						<%
							if (produto.getStatus()) {
						%>
						<input type="radio" id="ativo" name="status" checked="checked"
							required="required" value="true"><label for="ativo">Ativo</label>
						<input type="radio" id="inativo" name="status" required="required"
							value="false"><label for="inativo">Inativo</label>
						<%
							} else {
						%>
						<input type="radio" id="ativo" name="status" required="required"
							value="true"><label for="ativo">Ativo</label> <input
							type="radio" id="inativo" name="status" checked="checked"
							required="required" value="false"><label for="inativo">Inativo</label>
						<%
							}
						%>
					</div>
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

<%@include file="include/footer.jsp"%>