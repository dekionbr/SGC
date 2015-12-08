package com.SGC.domain;

public enum TipoProduto {
	Medicamento {
		public String toString() {
			return "Medicamento";
		}
	},
	Material_Hospitalar {
		public String toString() {
			return "Material Hospitalar";
		}
	},
	Material_de_Expediente {
		public String toString() {
			return "Material de Expediente";
		}
	}
}
