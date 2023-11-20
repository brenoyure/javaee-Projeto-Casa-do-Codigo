package br.com.casadocodigo.loja.models;

import static java.math.BigDecimal.valueOf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.casadocodigo.loja.daos.CompraDao;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;

@Named @SessionScoped
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompraDao compraDao;

	private Set<CarrinhoItem> itens = new HashSet<>();

	public void add(CarrinhoItem item) {
		itens.add(item);
	}

	public List<CarrinhoItem> getItens() {
		return new ArrayList<>(itens);
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getLivro().getPreco().multiply(valueOf(item.getQuantidade()));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (CarrinhoItem item : itens) {
			total = total.add(item.getLivro().getPreco().multiply(valueOf(item.getQuantidade())));
		}

		return total;
	}

	public void remover(CarrinhoItem carrinhoItem) {
		this.itens.remove(carrinhoItem);
	}

	public void finalizar(Usuario usuario) {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		compra.setItens(toJson());
		compraDao.salvar(compra);

		Client client = ClientBuilder.newClient();
		Pagamento pagamento = new Pagamento(getTotal());
		String target = "http://localhost:8080/payment/payment";
		
		Entity<Pagamento> json = Entity.json(pagamento);
		WebTarget webTarget = client.target(target);
		Builder requestBuilder = webTarget.request();
		String response = requestBuilder.post(json, String.class);

		System.out.println(response);

	}

	public String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		itens.forEach(item -> {
			builder.add(Json.createObjectBuilder()
				.add("titulo",item.getLivro().getTitulo())
				.add("preco", item.getLivro().getPreco())
				.add("quantidade", item.getQuantidade())
				.add("total", item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade().intValue()))));
		});

		return builder.build().toString();

	}


}
