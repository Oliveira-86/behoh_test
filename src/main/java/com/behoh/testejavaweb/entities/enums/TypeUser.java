package com.behoh.testejavaweb.entities.enums;

public enum TypeUser {

	PHYSICALPERSON(1, "Physical Person"),
	LEGALPERSON(2, "Legal Person");
	
	private Integer cod;
	private String description;
	
	private TypeUser(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static TypeUser toEnum(Integer cod) {
		
		if(cod == null) {
			return null;	
		}
		
		for (TypeUser x : TypeUser.values()) {
			if(cod.equals(x.getCod())) {
			return x;
			}
		}
	
		throw new IllegalArgumentException("Id invalid " + cod);
	}
}
