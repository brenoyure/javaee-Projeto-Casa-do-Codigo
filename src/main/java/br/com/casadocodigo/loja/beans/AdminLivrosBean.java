package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named @RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	@Inject
	private LivroDao dao;

	private List<Integer> autoresId = new ArrayList<>();

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext context;

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

	@Transactional
	public String salvar() {
		for(Integer autorId: autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}
		dao.salvar(livro);
		context
			.getExternalContext()
			.getFlash()
			.setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro " + livro.getTitulo() + " cadastrado com sucesso."));
		return "/livros/lista?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
