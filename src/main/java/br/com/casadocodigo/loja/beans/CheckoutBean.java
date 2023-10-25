package br.com.casadocodigo.loja.beans;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named @RequestScoped
public class CheckoutBean {

	@Inject
	private CarrinhoCompras carrinho;

	private Usuario usuario = new Usuario();

	@Transactional
	public void finalizar() {
		carrinho.finalizar(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
