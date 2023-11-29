package br.com.casadocodigo.loja.daos;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.com.casadocodigo.loja.models.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AutorDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Autor autor) {
		entityManager.persist(autor);
	}

	public List<Autor> listar() {
		return entityManager
				.createQuery("SELECT a FROM Autor a", 
						Autor.class)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

}
