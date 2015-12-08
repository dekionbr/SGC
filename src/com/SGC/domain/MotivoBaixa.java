package com.SGC.domain;

public enum MotivoBaixa {
	REGULAR {
		public String toString() {
			return "Baixa Regular";
		}
	},
	PERDA {
		public String toString() {
			return "Perda";
		}
	},
	VENCIMENTO {
		public String toString() {
			return "Vencimento de Validade";
		}
	},
	DANIFICADO {
		public String toString() {
			return "Danificado";
		}
	},
	QUEBRADO {
		public String toString() {
			return "Quebrado";
		}
	}
}
