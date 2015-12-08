package com.SGC.domain;

import javax.persistence.Column;


public class Relatorio {
	
	@Column(name = "Coluna")
	private Long Coluna;
	
	@Column(name = "Label")
	private String Label;
	
	@Column(name = "Quantidade")
	private Long Quantidade;
	
	@Column(name = "Valor")
	private double Valor;
	
	@Column(name = "XLabel")
	private String XLabel;
	
	public Long getColuna() {
		return Coluna;
	}
	
	public void setColuna(Long coluna) {
		Coluna = coluna;
	}
	
	public String getLabel() {
		return Label;
	}
	
	public void setLabel(String label) {
		Label = label;
	}
	
	public Long getQuantidade() {
		return Quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		Quantidade = quantidade;
	}

	public double getValor() {
		return Valor;
	}

	public void setValor(double valor) {
		Valor = valor;
	}

	public String getXLabel() {
		return XLabel;
	}

	public void setXLabel(String xLabel) {
		XLabel = xLabel;
	}
}
