<%@page import="com.SGC.domain.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<header>
	<div id="logo">
		<a href="test.html">Sistema de Gerenciamento de compras</a>
	</div>
	<div id="header">
		<!-- <ul id="headernav">
				<li><ul>
					<li><a href="icons.html">Icons</a><span>300+</span></li>
					<li><a href="#">Submenu</a><span>4</span>
						<ul>
							<li><a href="#">Just</a></li>
							<li><a href="#">another</a></li>
							<li><a href="#">Dropdown</a></li>
							<li><a href="#">Menu</a></li>
						</ul>
					</li>
					<li><a href="login.html">Login</a></li>
					<li><a href="wizard.html">Wizard</a><span>Bonus</span></li>
					<li><a href="#">Errorpage</a><span>new</span>
						<ul>
							<li><a href="error-403.html">403</a></li>
							<li><a href="error-404.html">404</a></li>
							<li><a href="error-405.html">405</a></li>
							<li><a href="error-500.html">500</a></li>
							<li><a href="error-503.html">503</a></li>
						</ul>
					</li>
				</ul></li>
			</ul> 
			<div id="searchbox">
				<form id="searchform">
					<input type="search" name="query" id="search" placeholder="Search">
				</form>
			</div> -->
		<div style="float: right; margin: 30px 10px 17px 0;">
			<span>Olá, <%=((Funcionario) session.getAttribute("usuario")).getNome()%>
			</span>
		</div>
	</div>
</header>