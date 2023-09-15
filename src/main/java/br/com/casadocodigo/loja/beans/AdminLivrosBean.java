package br.com.casadocodigo.loja.beans;

import java.io.IOException;
import java.util.List;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;

@Named @RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	@Inject
	private LivroDao dao;

	@Inject
	private AutorDao autorDao;

	@Inject
	private FacesContext context;

	private Part capaLivro;

	@Transactional
	public String salvar() throws IOException {
		dao.salvar(livro);

		FileSaver fileSaver = new FileSaver();
		String relativePath = fileSaver.write(capaLivro, "livros");
		livro.setCapaPath(relativePath);

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

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

}
