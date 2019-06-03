package br.unitins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.Util;
import br.unitins.dao.UsuarioDAO;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -5348786207056352984L;

	private Usuario usuario, usuarioAux;

	private List<Usuario> listaUsuario;

	public boolean entrar() {
		UsuarioDAO dao = new UsuarioDAO();

		// Lista todos os usuários para serem verificados
		this.setListaUsuario(dao.findAll());

		if (!this.getListaUsuario().isEmpty()) {

			if (!this.validarLogin()) {
				Util.addMessageError("Usuário não Cadastrado!");
				this.limpar();
				return false;
			}

			if (this.validarSenha()) {
				Util.redirect("usuario.xhtml");
				this.limpar();
			} else {
				Util.addMessageError("Senha Incorreta!");
				this.getUsuario().setSenha(null);
			}
		} else {
			Util.addMessageError("Não existe nenhum usuário cadastrado!");
			this.limpar();
		}
		return false;
	}

	public void cancelar() {
		this.limpar();
	}

	public void limpar() {
		this.usuario = null;
	}

	private boolean validarLogin() {

		// Define o usuário a ser verificado
		for (Usuario usuario : listaUsuario) {
			if (this.getUsuario().getLogin().equals(usuario.getLogin())) {
				this.setUsuarioAux(usuario);
				return true;
			}
		}

		return false;
	}

	private boolean validarSenha() {
		String encrypt = Util.encrypt(this.getUsuario().getSenha());

		if (encrypt.equals(this.getUsuarioAux().getSenha()))
			return true;
		else
			return false;
	}

	public Usuario getUsuario() {
		if (this.usuario == null)
			this.setUsuario(new Usuario());
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		if (this.listaUsuario == null)
			this.setListaUsuario(new ArrayList<Usuario>());
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuarioAux() {
		if (this.usuarioAux == null)
			this.setUsuarioAux(new Usuario());
		return usuarioAux;
	}

	public void setUsuarioAux(Usuario usuarioAux) {
		this.usuarioAux = usuarioAux;
	}
}