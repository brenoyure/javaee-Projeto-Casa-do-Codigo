package br.com.casadocodigo.loja.daos;

import br.com.casadocodigo.loja.models.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Livro livro) {
		manager.persist(livro);
	}

}
