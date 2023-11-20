package br.com.casadocodigo.loja.services;

import static jakarta.ws.rs.client.ClientBuilder.newClient;
import static jakarta.ws.rs.client.Entity.json;

import java.math.BigDecimal;

import br.com.casadocodigo.loja.models.Pagamento;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
public class PagamentoGateway {

	private static final String PAGAMENTO_WEB_TARGET = "http://localhost:8080/payment/payment";

	public Response pagar(BigDecimal valor) {
		return newClient()
					.target(PAGAMENTO_WEB_TARGET)
					.request(MediaType.APPLICATION_JSON)
					.post(json(new Pagamento(valor)));

	}

}
