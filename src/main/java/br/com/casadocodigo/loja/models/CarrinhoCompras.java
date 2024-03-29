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

	public void finalizar(Compra compra) {
		compra.setItens(toJson());
		compra.setTotal(getTotal());
		compraDao.salvar(compra);

//		String response = pagamentoGateway.pagar(getTotal()).readEntity(Pagamento.class).getValue().toString();
//		System.out.println(response);
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
