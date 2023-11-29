package br.com.casadocodigo.loja.resources;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("livros")
public class LivroResource {

	@Inject
	private LivroDao livroDao;

	@GET
	@Path("lancamentos")
	@Produces({APPLICATION_JSON, APPLICATION_XML})
	public List<Livro> ultimosLancamentos() {
		return livroDao.listarUltimosLancamentos();
	}

}
