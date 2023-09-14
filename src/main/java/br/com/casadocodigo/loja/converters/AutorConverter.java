package br.com.casadocodigo.loja.converters;

import br.com.casadocodigo.loja.models.Autor;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "autorConverter")
public class AutorConverter implements Converter<Autor> {

	@Override
	public Autor getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank()) {
			return null;
		}
		Autor autor = new Autor();
		autor.setId(Integer.valueOf(id));
		return autor;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Autor autor) {
		if (autor == null) {
			return null;
		}
		return String.valueOf(autor.getId());
	}

}
