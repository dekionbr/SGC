package com.SGC.domain;

public enum TipoContato {
	Telefone {
		public String toString() {
			return "Telefone";
		}
	},
	EMail {
		public String toString() {
			return "E-Mail";
		}
	},
	Celular {
		public String toString() {
			return "Celular";
		}
	}
}
