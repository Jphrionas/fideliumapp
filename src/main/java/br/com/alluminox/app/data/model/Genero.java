package br.com.alluminox.app.data.model;

public enum Genero {
	M("Masculino"),
	F("Feminino");
	
	private String value;

	private Genero(String value) {
		this.value = value;		
	}

	public String getValue() {
		return value;
	}
}
