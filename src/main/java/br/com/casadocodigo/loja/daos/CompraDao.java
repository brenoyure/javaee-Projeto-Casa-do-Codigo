package br.com.casadocodigo.loja.daos;

import java.io.Serializable;

import br.com.casadocodigo.loja.models.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CompraDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Compra compra) {
		entityManager.persist(compra);
	}

}
