package com.behoh.testejavaweb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.behoh.testejavaweb.entities.enums.TypeUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_tb")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique=true)
	private String email;
	private String cpfOuCnpj;
	private Integer type;
	
	@ElementCollection
	@CollectionTable(name = "PHONE")
	private Set<String> phone = new HashSet<>();
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="user_event",
			joinColumns = @JoinColumn(name="user_tb"),
			inverseJoinColumns = @JoinColumn(name="event_tb")		
		)
	private List<Event> events = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String name, String email, String cpfOuCnpj, TypeUser type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.type = (type==null ? null : type.getCod());
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

	public TypeUser getType() {
		return TypeUser.toEnum(type);
	}

	public void setType(TypeUser type) {
		this.type = type.getCod();
	}

	public Set<String> getPhone() {
		return phone;
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public List<Address> getAdrresses() {
		return addresses;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}