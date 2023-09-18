package br.com.casadocodigo.loja.beans;

import java.util.List;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class HomeBean {

	@Inject
	private LivroDao dao;

	public List<Livro> ultimosLancamentos() {
		return dao.listarUltimosLancamentos();
	}

	public List<Livro> demaisLivros() {
		return dao.demaisLivros();
	}

}
