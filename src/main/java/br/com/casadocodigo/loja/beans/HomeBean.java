package br.com.casadocodigo.loja.beans;

import java.util.List;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class HomeBean {

	@Inject
	private LivroDao dao;

	public List<Livro> ultimosLancamentos() {
		return dao.listarUltimosLancamentos();
	}

}
