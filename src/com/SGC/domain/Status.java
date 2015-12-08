package com.SGC.domain;

public enum Status {
	Pendente {
		public String toString() {
			return "Pendente";
		}
	},
	Entregue {
		public String toString() {
			return "Entregue";
		}
	},
	Cancelado {
		public String toString() {
			return "Cancelado";
		}
	}
}
