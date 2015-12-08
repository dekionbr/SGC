<%@page import="java.util.List"%>
<%@page import="com.SGC.business.*"%>
<%@page import="com.SGC.domain.*"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%
	Funcionario funcionario = (Funcionario) request
			.getAttribute("Funcionario");
%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>


<section id="content">

	<div class="g12">
		<h1>
			Cadastro de Funcionários <span><a href="ajuda.jsp"
				class="small">Ajuda</a></span>
		</h1>

		<p>Painel de cadastros de funcionários</p>


		<form action="IncluirFuncionario" method="post" autocomplete="On">

			<input type="hidden" id="Id" name="Id"
				value="<%=funcionario.getId()%>">

			<fieldset>
				<label>Dados do Funcionário</label>
				<section>
					<label for="Nome">Nome</label>
					<div>
						<input type="text" id="Nome" name="Nome"
							value="<%=funcionario.getNome()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Nome">RG</label>
					<div>
						<input type="text" id="RG" name="RG"
							value="<%=funcionario.getRG()%>" required="required"
							data-regex="^\d{2}.\d{3}.\d{3}-\d{1}$"> <span>Exemplo:
							00.000.000-0</span>
					</div>
				</section>
				<section>
					<label for="Nome">CPF</label>
					<div>
						<input type="text" id="CPF" name="CPF"
							value="<%=funcionario.getCPF()%>" required="required"
							data-regex="^\d{3}.\d{3}.\d{3}-\d{2}$"> <span>Exemplo:
							000.000.000-00</span>
					</div>
				</section>
				<section>
					<label for="Nome">Endereço</label>
					<div>
						<input type="text" id="Endereco" name="Endereco"
							value="<%=funcionario.getEndereco()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Numero">Número</label>
					<div>
						<input type="text" id="Numero" name="Numero"
							value="<%=funcionario.getNumero()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Cidade">Cidade</label>
					<div>
						<input type="text" id="Cidade" name="Cidade"
							value="<%=funcionario.getCidade()%>" required="required">
					</div>
				</section>
				<section>
					<label for="UF">UF</label>
					<div>
						<input type="text" id="UF" name="UF"
							value="<%=funcionario.getUF()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Bairro">Bairro</label>
					<div>
						<input type="text" id="Bairro" name="Bairro"
							value="<%=funcionario.getBairro()%>" required="required">
					</div>
				</section>
				<section>
					<label for="CEP">CEP</label>
					<div>
						<input type="text" id="CEP" name="CEP"
							value="<%=funcionario.getCEP()%>" required="required"
							data-regex="^\d{5}-\d{3}$"> <span>Exemplo:
							00000-000</span>
					</div>
				</section>
				<section>
					<label for="email">Email</label>
					<div>
						<input id="Email" name="Email" type="email"
							value="<%=funcionario.getEmail()%>">
					</div>
				</section>
				<section>
					<label for="Funcao">Função</label>
					<div>
						<select name="Funcao" id="Funcao" required="required">
							<option value="">Selecione a função</option>
							<%
								List<TipoUsuario> tUsuarios = (List<TipoUsuario>) request
										.getAttribute("TipoUsuarios");
								for (TipoUsuario tUsuario : tUsuarios) {
									String selected = funcionario.getTipoUsuario().getId() == tUsuario
											.getId() ? "selected=\"selected\"" : "";
							%>
							<option value="<%=tUsuario.getId()%>" <%=selected%>><%=tUsuario.getValor()%></option>
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
							if (funcionario.getStatus()) {
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
				<label>Dados de Acesso ao sistema</label>
				<section>
					<label for="Passe">Login</label>
					<div>
						<input type="text" id="Passe" name="Passe"
							value="<%=funcionario.getPasse()%>" required="required">
					</div>
				</section>
				<section>
					<label for="password">Senha<br></label>
					<div>
						<%
							if (funcionario.getSenha().length() > 0) {
						%>
						<input type="password" id="password" name="password">
						<%
							} else {
						%>
						<input type="password" id="password" name="password"
							required="required">
						<%
							}
						%>
					</div>
				</section>
			</fieldset>

			<fieldset>
				<section>
					<div>
						<button class="submit" name="save" value="Salvar">Salvar</button>
						<button class="reset">Limpar</button>
					</div>
				</section>
			</fieldset>
		</form>
	</div>
</section>

<%@include file="include/footer.jsp"%>