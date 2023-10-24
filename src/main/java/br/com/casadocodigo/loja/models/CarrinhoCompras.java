package br.com.casadocodigo.loja.models;

import static java.math.BigDecimal.valueOf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named @SessionScoped
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

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

}
