package br.unitins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.Util;
import br.unitins.dao.UsuarioDAO;
import br.unitins.model.Perfil;
import br.unitins.model.Telefone;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -8057267128084977821L;

	private Usuario usuario;

//	private Telefone telefone;

	private List<Usuario> listaUsuario;

	public void editar(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		this.setUsuario(dao.findById(id));
	}

	public void incluir() {
		UsuarioDAO dao = new UsuarioDAO();

		this.encriptografar();
				
		if (dao.create(this.getUsuario())) {
			limpar();
			// Para atualizar o data table
			this.listaUsuario = null;
		}

		dao.closeConnection();
	}

	public void alterar() {
		UsuarioDAO dao = new UsuarioDAO();

		if (dao.update(this.getUsuario())) {
			limpar();
			// Para atualizar o data table
			this.listaUsuario = null;
		}

		dao.closeConnection();
	}

	public void excluir() {
		UsuarioDAO dao = new UsuarioDAO();
		
		if (dao.delete(this.getUsuario().getId())) {
			limpar();
			// para atualizar o data table
			this.listaUsuario = null;
		}
		
		dao.closeConnection();
	}

//	public void adicionarTelefone() {
//		if (getUsuario().getListaTelefone() == null)
//			getUsuario().setListaTelefone(new ArrayList<Telefone>());
//
//		getUsuario().getListaTelefone().add(getTelefone());
//		setTelefone(null);
//	}
//
//	public void removerTelefone(Telefone telefone) {
//		getUsuario().getListaTelefone().remove(telefone);
//	}

	public void limpar() {
		usuario = null;
//		telefone = null;
	}
	
	private void encriptografar() {
		// Encriptografando a senha do usuário
		String encrypt = Util.encrypt(this.getUsuario().getSenha());
		
		if(encrypt != null)
			this.getUsuario().setSenha(encrypt);
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findAll();
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
			dao.closeConnection();
		}

		return listaUsuario;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	public Telefone getTelefone() {
//		if (telefone == null)
//			telefone = new Telefone();
//		return telefone;
//	}
//
//	public void setTelefone(Telefone telefone) {
//		this.telefone = telefone;
//	}
}