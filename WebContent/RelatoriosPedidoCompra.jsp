<%@page import="com.SGC.domain.*"%>
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
		<p>Painel de Relatórios gerenciais</p>
		<form id="form" action="" method="post" autocomplete="off">
			<fieldset>
				<label>Relatório Mensal</label>
				<section>
					<label for="Periodo">Período</label>
					<div>
						<select name="Periodo" id="Periodo">
							<option value="1">01/2015</option>
							<option value="2">02/2015</option>
							<option value="3">03/2015</option>
							<option value="4">04/2015</option>
							<option value="5">05/2015</option>
							<option value="6">06/2015</option>
							<option value="7">07/2015</option>
							<option value="8">08/2015</option>
							<option value="9">09/2015</option>
							<option value="10">10/2015</option>
							<option value="11">11/2015</option>
							<option value="12">12/2015</option>
						</select>
					</div>
				</section>
				<section>
					<label for="comparativo">Comparativo</label>
					<div>
						<select name="comparativo" id="comparativo">
							<option value="">Selecione</option>
							<option value="1">01/2015</option>
							<option value="2">02/2015</option>
							<option value="3">03/2015</option>
							<option value="4">04/2015</option>
							<option value="5">05/2015</option>
							<option value="6">06/2015</option>
							<option value="7">07/2015</option>
							<option value="8">08/2015</option>
							<option value="9">09/2015</option>
							<option value="10">10/2015</option>
							<option value="11">11/2015</option>
							<option value="12">12/2015</option>
						</select>
					</div>
				</section>
			</fieldset>
		</form>
		<br>
		<hr>
		<h3>Pedido de Compra</h3>
		<table class="chart" data-type="bars">
			<thead>
				<tr>
					<th></th>
					<th>01/2015</th>
					<th>02/2015</th>
					<th>03/2015</th>
					<th>04/2015</th>
					<th>05/2015</th>
					<th>06/2015</th>
					<th>07/2015</th>
					<th>08/2015</th>
					<th>09/2015</th>
					<th>10/2015</th>
					<th>11/2015</th>
					<th>12/2015</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>Marcos Ricardo</th>
					<td>48</td>
					<td>125</td>
					<td>159</td>
					<td>147</td>
					<td>154</td>
					<td>114</td>
					<td>8</td>
					<td>27</td>
					<td>25</td>
					<td>79</td>
					<td>47</td>
					<td>59</td>
				</tr>
				<tr>
					<th>Jorge Silva</th>
					<td>27</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
					<td>59</td>
					<td>147</td>
					<td>47</td>
					<td>48</td>
					<td>125</td>
					<td>159</td>
					<td>114</td>
					<td>154</td>
				</tr>
				<tr>
					<th>JosÃ© Mariano</th>
					<td>28</td>
					<td>56</td>
					<td>98</td>
					<td>112</td>
					<td>87</td>
					<td>26</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
					<td>59</td>
					<td>147</td>
					<td>47</td>
				</tr>
				<tr>
					<th>Marcelo Belgamo</th>
					<td>38</td>
					<td>43</td>
					<td>69</td>
					<td>54</td>
					<td>16</td>
					<td>16</td>
					<td>112</td>
					<td>87</td>
					<td>26</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
				</tr>
				<tr>
					<th>Rui Menezes</th>
					<td>112</td>
					<td>87</td>
					<td>26</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
					<td>59</td>
					<td>147</td>
					<td>47</td>
					<td>48</td>
					<td>125</td>
					<td>87</td>
				</tr>
			</tbody>
		</table>
		<br />
		<hr />
		<table>
			<thead>
				<tr>
					<th></th>
					<th>01/2015</th>
					<th>02/2015</th>
					<th>03/2015</th>
					<th>04/2015</th>
					<th>05/2015</th>
					<th>06/2015</th>
					<th>07/2015</th>
					<th>08/2015</th>
					<th>09/2015</th>
					<th>10/2015</th>
					<th>11/2015</th>
					<th>12/2015</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>Marcos Ricardo</th>
					<td>48</td>
					<td>125</td>
					<td>159</td>
					<td>147</td>
					<td>154</td>
					<td>114</td>
					<td>8</td>
					<td>27</td>
					<td>25</td>
					<td>79</td>
					<td>47</td>
					<td>59</td>
				</tr>
				<tr>
					<th>Jorge Silva</th>
					<td>27</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
					<td>59</td>
					<td>147</td>
					<td>47</td>
					<td>48</td>
					<td>125</td>
					<td>159</td>
					<td>114</td>
					<td>154</td>
				</tr>
				<tr>
					<th>JosÃ© Mariano</th>
					<td>28</td>
					<td>56</td>
					<td>98</td>
					<td>112</td>
					<td>87</td>
					<td>26</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
					<td>59</td>
					<td>147</td>
					<td>47</td>
				</tr>
				<tr>
					<th>Marcelo Belgamo</th>
					<td>38</td>
					<td>43</td>
					<td>69</td>
					<td>54</td>
					<td>16</td>
					<td>16</td>
					<td>112</td>
					<td>87</td>
					<td>26</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
				</tr>
				<tr>
					<th>Rui Menezes</th>
					<td>112</td>
					<td>87</td>
					<td>26</td>
					<td>8</td>
					<td>25</td>
					<td>79</td>
					<td>59</td>
					<td>147</td>
					<td>47</td>
					<td>48</td>
					<td>125</td>
					<td>87</td>
				</tr>
			</tbody>
		</table>

	</div>

</section>

<%@include file="include/footer.jsp"%>