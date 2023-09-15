package br.com.casadocodigo.loja.daos;

import java.util.List;

import br.com.casadocodigo.loja.models.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		return manager
				.createQuery("SELECT DISTINCT(l) FROM Livro l JOIN FETCH l.autores", 
						Livro.class)
				.getResultList();
	}

	public List<Livro> listarUltimosLancamentos() {
		return manager
				 .createQuery("SELECT l FROM Livro l ORDER BY l.id DESC", Livro.class)
				 .setMaxResults(5)
				 .getResultList();
	}

}
