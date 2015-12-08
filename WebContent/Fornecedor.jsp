<%@page import="com.SGC.business.ContatoBusiness"%>
<%@page import="java.util.Set"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.SGC.business.FornecedorBusiness"%>
<%@page import="com.SGC.domain.Fornecedor"%>
<%@page import="com.SGC.domain.Contato"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@include file="include/top.jsp"%>

<%@include file="include/header.jsp"%>

<%@include file="include/menu.jsp"%>
<%
	Fornecedor fornecedor = (Fornecedor) request
			.getAttribute("Fornecedor");
%>

<section id="content">

	<div class="g12">
		<h1>
			Cadastro de Fornecedor <span><a href="ajuda.html"
				class="small">Ajuda</a></span>
		</h1>
		<p>Painel de cadastros de forncedor</p>


		<form action="IncluirFornecedor" method="post" autocomplete="on">

			<input type="hidden" id="Id" name="Id"
				value="<%=fornecedor.getId() %>">

			<fieldset>
				<label>Dados Fornecededor</label>
				<section>
					<label for="Nome">Nome Fantasia</label>
					<div>
						<input type="text" id="Nome" name="Nome"
							value="<%=fornecedor.getNomeFantasia()%>" required="required">
					</div>
				</section>
				<section>
					<label for="razaosocial">Razão Social</label>
					<div>
						<input type="text" id="RazaoSocial" name="RazaoSocial"
							value="<%=fornecedor.getRazaoSocial()%>" required="required">
					</div>
				</section>
				<section>
					<label for="CNPJ">CNPJ</label>
					<div>
						<input type="text" id="CNPJ" name="CNPJ"
							placeholder="00.000.000/0000-00"
							value="<%=fornecedor.getCNPJ()%>" required="required"
							data-regex="^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$" maxlength="18">
						<span>Exemplo: 00.000.000/0000-00</span>
					</div>
				</section>
				<section>
					<label for="Nome">Endereço</label>
					<div>
						<input type="text" id="Endereco" name="Endereco"
							value="<%=fornecedor.getEndereco()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Numero">Número</label>
					<div>
						<input type="text" id="Numero" name="Numero"
							value="<%=fornecedor.getEndereco()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Cidade">Cidade</label>
					<div>
						<input type="text" id="Cidade" name="Cidade"
							value="<%=fornecedor.getCidade()%>" required="required">
					</div>
				</section>
				<section>
					<label for="UF">UF</label>
					<div>
						<input type="text" id="UF" name="UF"
							value="<%=fornecedor.getUF()%>" required="required">
					</div>
				</section>
				<section>
					<label for="Bairro">Bairro</label>
					<div>
						<input type="text" id="Bairro" name="Bairro"
							value="<%=fornecedor.getBairro()%>" required="required">
					</div>
				</section>
				<section>
					<label for="CEP">CEP</label>
					<div>
						<input type="text" id="CEP" name="CEP"
							value="<%=fornecedor.getCEP()%>" placeholder="00000-000"
							data-regex="^\d{5}-\d{3}$" required="required" maxlength="9">
						<span>Exemplo: 00000-000</span>
					</div>
				</section>
				<section>
					<label>Situação</label>
					<div>
						<%
							if (fornecedor.getStatus()) {
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
				<section>
					<label for="representante">Representante</label>
					<div>
						<input type="text" id="representante" name="representante"
							value="<%=fornecedor.getNomeRepresentante()%>"
							required="required">
					</div>
				</section>
				<section>
					<label>Contatos</label>
					<div>
						<%
						List<Contato> contatos = fornecedor.getContatos();
						%>
						<div>
							<div id="dialog-form">
								<form>
									<fieldset>
										<section>
											<label for="telefone">Tipo Contato</label>
											<div>
												<select name="Meio" id="Meio" required="required">
													<option value="">Selecione a função</option>
													<option value="0">Telefone</option>
													<option value="1">E-Mail</option>
													<option value="2">Celular</option>
												</select>
											</div>
										</section>
										<section>
											<label for="email">Valor</label>
											<div>
												<input type="text" id="Valor" name="valor"
													required="required">
											</div>
										</section>
										<section>
											<div>
												<input type="submit" tabindex="-1"
													style="position: absolute; top: -1000px">
											</div>
										</section>
									</fieldset>
								</form>
							</div>
							<div>
								<button id="create-user">Incluir contato</button>
								<br /> <br />
								<table id="contatos">
									<thead>
										<tr>
											<th>Meio de Contato</th>
											<th>Contato</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<%
						for (Contato contato : contatos) {
							int i = contatos.indexOf(contato);
					%>
										<tr data-hidden="contato_<%=i%>">
											<td><%=contato.getTipoContato().toString()%></td>
											<td><%=contato.getValor()%></td>
											<td><a id="btn_excluir_row_temp_<%=i%>" class="Excluir">excluir</a></td>
										</tr>
										<%
						}
					%>
									</tbody>
									<%
						for (Contato contato : contatos) {		
							int i = contatos.indexOf(contato);
					%>
									<input type="hidden" id="contato_<%=i %>_Id"
										class="contato_<%=i %>" name="Contato[<%=i %>].Id"
										value="<%=contato.getId() %>" />
									<input type="hidden" id="contato_<%=i %>_TipoContato"
										class="contato_<%=i %>" name="Contato[<%=i %>].TipoContato"
										value="<%=contato.getTipoContato().ordinal() %>" />
									<input type="hidden" id="contato_<%=i %>_Valor"
										class="contato_<%=i %>" name="Contato[<%=i %>].Valor"
										value="<%=contato.getValor() %>" />
									<%
						}
					%>
								</table>
								<span>Entre com os contatos do fornecedor</span>
							</div>
						</div>
					</div>
				</section>
				<section>
					<div>
						<button class="submit" name="Cadastrar" value="Cadastrar">Cadastrar</button>
						<button class="reset">Limpar</button>
					</div>
				</section>
			</fieldset>
		</form>
</section>

<script>
	$(function() {
		var dialog, form, emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/, meio = $("#Meio"), valor = $("#Valor"), allFields = $(
				[]).add(meio).add(valor), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				//updateTips("Length of " + n + " must be between " + min
				//		+ " and " + max + ".");
				$.msg("O campo " + n + " precisa ser preenchido corretamente!");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				//updateTips(n);
				return false;
			} else {
				return true;
			}
		}

		var count = 0;

		function addUser() {
			var valid = true;
			// 			allFields.removeClass("ui-state-error");

			// 			valid = valid && checkLength(meio, "username", 3, 16);
			// 			valid = valid && checkLength(valor, "email", 6, 80);

			// 			valid = valid && checkRegexp(
			// 					valor,
			// 							/^[a-z]([0-9a-z_\s])+$/i,
			// 							"Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
			// 			valid = valid
			// 					&& checkRegexp(email, emailRegex, "eg. ui@jquery.com");
			// 			valid = valid
			// 					&& checkRegexp(password, /^([0-9a-zA-Z])+$/,
			// 							"Password field only allow : a-z 0-9");

			valid = valid && checkLength(meio, "Tipo Contato", 1, 20);
			valid = valid && checkLength(valor, "Valor", 3, 99);

			var arr = [ "Telefone", "E-Mail", "Celular" ], //colocar via programação
			value = meio.val(), titulo = arr[value];

			if (valid) {

				$("#contatos")
						.after(
								"<input type=\"hidden\" id=\"contato_" + count
										+ "_Id\" class=\"contato_" + count + "\" name=\"Contato["
										+ count + "].Id\" value=\"0\" />")
						.after(
								"<input type=\"hidden\" id=\"contato_" + count
										+ "_TipoContato\" class=\"contato_" + count + "\" name=\"Contato["
										+ count + "].TipoContato\" value=\""
										+ value + "\" />")
						.after(
								"<input type=\"hidden\" id=\"contato_" + count
										+ "_Valor\" class=\"contato_" + count
										+ "\" name=\"Contato[" + count
										+ "].Valor\" value=\"" + valor.val()
										+ "\" />");

				$("#contatos tbody")
						.append(
								"<tr data-hidden=\"contato_" + count + "\">"
										+ "<td>"
										+ titulo
										+ "</td>"
										+ "<td>"
										+ valor.val()
										+ "</td>"
										+ "<td><a id=\"btn_excluir_row_temp_"+count+"\" class=\"Excluir\">excluir</a></td>"
										+ "</tr>")
						.find("#btn_excluir_row_temp_" + count)
						.click(
								function(e) {
									var $row = $(this).parent().parent(), $IdHidden = $row
											.data("hidden"), $hidden = $("."
											+ $IdHidden);
									$row.remove();
									$hidden.remove();
								});

				dialog.dialog("close");
				count++;
			}
			return valid;
		}

		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 280,
			width : 600,
			modal : true,
			header : "Formulário de Contatos",
			buttons : {
				"Adicionar Contato" : addUser,
				Cancel : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				meio.val("");
				valor.val("");
			}
		});

		// 		form = dialog.find("form").on("submit", function(event) {
		// 			event.preventDefault();
		// 			addUser();
		// 		});

		$("#create-user").click(function() {
			dialog.dialog("open");
		});

		$(".Excluir")
				.each(
						function(index) {
							$(this)
									.one(
											"click",
											function() {

												var $row = $(this).parent()
														.parent(), $IdHidden = $row
														.data("hidden"), $hidden = $("#"
														+ $IdHidden + "_Id");

												var data = {
													id : $hidden.val()
												};

												$
														.ajax({
															type : "POST",
															url : "DeletarContato",
															data : data,
															success : function(
																	e) {
																var obj = jQuery
																		.parseJSON(e);
																if (obj.sucess) {
																	$
																			.msg(
																					obj.msg,
																					{
																						header : 'Mensagem'
																					});
																	$row
																			.remove();
																} else {
																	$
																			.msg(
																					obj.msg,
																					{
																						header : 'Mensagem'
																					});
																}
															}
														});
											});
						});

	});
</script>

<%@include file="include/footer.jsp"%>
