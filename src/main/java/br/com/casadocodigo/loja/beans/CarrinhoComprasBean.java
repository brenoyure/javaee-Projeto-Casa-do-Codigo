package br.com.casadocodigo.loja.beans;

import java.util.List;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Livro;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class CarrinhoComprasBean {

	@Inject
	private LivroDao dao;

	@Inject
	private CarrinhoCompras carrinho;

	public String add(Integer id) {
		Livro livro = dao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);

		return "carrinho.xhtml?faces-redirect=true";

	}

	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}

}
