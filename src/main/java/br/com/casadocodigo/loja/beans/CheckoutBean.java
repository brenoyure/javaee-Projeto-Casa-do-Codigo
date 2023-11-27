package br.com.casadocodigo.loja.beans;

import static jakarta.ws.rs.core.Response.Status.TEMPORARY_REDIRECT;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.Compra;
import br.com.casadocodigo.loja.models.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Named @RequestScoped
public class CheckoutBean {

	@Inject
	private CarrinhoCompras carrinho;

	@Inject
	private FacesContext facesContext;

	private Usuario usuario = new Usuario();

	@Transactional
	public void finalizar() {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		carrinho.finalizar(compra);

		String contextName = facesContext.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.setStatus(TEMPORARY_REDIRECT.getStatusCode());
		response.setHeader("Location", contextName + "/service/pagamento?uuid=" + compra.getUuid());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
