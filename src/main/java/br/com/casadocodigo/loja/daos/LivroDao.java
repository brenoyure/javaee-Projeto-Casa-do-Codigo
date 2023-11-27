package br.com.casadocodigo.loja.daos;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.com.casadocodigo.loja.models.Livro;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

@Stateful
public class LivroDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}

	public Livro buscarPorId(Integer id) {
		return entityManager.find(Livro.class, id);
/*		return entityManager
				  .createQuery("SELECT l FROM Livro l INNER JOIN FETCH l.autores WHERE l.id=?1", Livro.class)
				  .setParameter(1, id)
				  .getSingleResult();
*/
	}

	public List<Livro> listar() {
		return entityManager
				.createQuery("SELECT DISTINCT(l) FROM Livro l JOIN FETCH l.autores", 
						Livro.class)
				.getResultList();
	}

	public List<Livro> listarUltimosLancamentos() {
		return entityManager
				 .createQuery("SELECT l FROM Livro l ORDER BY l.id DESC", Livro.class)
				 .setMaxResults(5)
				 .setHint(HINT_CACHEABLE, true)
				 .getResultList();
	}

	public List<Livro> demaisLivros() {
		return entityManager
				 .createQuery("SELECT l FROM Livro l ORDER BY l.id DESC", Livro.class)
				 .setFirstResult(5)
				 .setHint(HINT_CACHEABLE, true)
				 .getResultList();
	}

}
