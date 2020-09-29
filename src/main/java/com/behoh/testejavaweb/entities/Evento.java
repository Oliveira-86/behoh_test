package com.behoh.testejavaweb.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer vagas;
	private Date DataIn;
	private Date Datafin;
	
	public Evento() {
	}

	public Evento(Long id, String nome, Integer vagas, Date dataIn, Date datafin) {
		super();
		this.id = id;
		this.nome = nome;
		this.vagas = vagas;
		DataIn = dataIn;
		Datafin = datafin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	public Date getDataIn() {
		return DataIn;
	}

	public void setDataIn(Date dataIn) {
		DataIn = dataIn;
	}

	public Date getDatafin() {
		return Datafin;
	}

	public void setDatafin(Date datafin) {
		Datafin = datafin;
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
