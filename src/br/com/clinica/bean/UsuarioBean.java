package br.com.clinica.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.clinica.domain.Usuario;

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class UsuarioBean {
	private ListDataModel<Usuario> itens;

	public ListDataModel<Usuario> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Usuario> itens) {
		this.itens = itens;
	}
	

}
