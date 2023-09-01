package br.com.casadocodigo.loja.daos;

import java.util.List;

import br.com.casadocodigo.loja.models.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AutorDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Autor> listar() {
		var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Autor.class);
		criteriaQuery.from(Autor.class);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
