package com.behoh.testejavaweb.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.services.validation.UserUpdate;

@UserUpdate
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message= "Mandatory")
	@Length(min=5, max=30, message="The length should be between 5 and 30 characters")
	private String name;
	
	@NotEmpty(message= "Mandatory")
	@Email(message= "Email Invalid")
	private String email;
	private String cpfOuCnpj;
	
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
}
