package br.com.casadocodigo.loja.services;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.models.Compra;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/pagamento")
public class PagamentoService {

	@Inject
	private CompraDao compraDao;

	@Inject
	private PagamentoGateway pagamentoGateway;

	@Context
	private ServletContext servletContext;

	private static ExecutorService executor = Executors.newFixedThreadPool(50) ;

	@POST
	@Produces(APPLICATION_JSON)
	public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Compra compra = compraDao.buscaPorUuid(uuid);

		executor.submit(() -> {
			pagamentoGateway.pagar(compra.getTotal());

			URI responseUri = UriBuilder
					.fromPath("http://localhost:8080" + servletContext.getContextPath() + "/index.xhtml")
					.queryParam("msg", "Compra Realizada com Sucesso").build();

			Response response = Response.seeOther(responseUri).build();
			ar.resume(response);

		});

	}

}
