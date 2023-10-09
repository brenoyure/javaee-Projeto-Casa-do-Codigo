package br.com.casadocodigo.loja.models;

import java.util.Objects;

public class CarrinhoItem {

	private Livro livro;
	private Integer quantidade;

	public CarrinhoItem() {

	}

	public CarrinhoItem(Livro livro) {
		this.livro = livro;
		this.quantidade = 1;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(livro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CarrinhoItem))
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		return Objects.equals(livro, other.livro);
	}

}
