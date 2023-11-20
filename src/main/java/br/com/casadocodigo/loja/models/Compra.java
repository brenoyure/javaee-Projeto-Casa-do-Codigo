package br.com.casadocodigo.loja.models;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.UUID.randomUUID;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Lob
	private String itens;

	/**
	 * Com o cascade = CascadeType.PERSIST conseguimos fazer com que o usuario seja
	 * persistido quando persistirmos uma compra.
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;

	private String uuid;

	@PrePersist
	public void createUuid() {
		this.uuid = randomUUID().toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItens() {
		return itens;
	}

	public void setItens(String itens) {
		this.itens = itens;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Compra))
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(id, other.id);
	}

}
