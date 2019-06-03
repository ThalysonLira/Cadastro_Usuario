package br.unitins.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Usuario extends DefaultEntity {

	@NotBlank(message="O nome deve ser informado.")
	private String nome;
	
	@Email(message="Email inválido.")
	private String login;
	
	@NotBlank(message="A senha deve ser informada.")
	@Size(min=6, message="Tamanho incompativel, a senha deve conter pelo menos 6 caracteres.")
	@Size(max=20, message="Tamanho incompativel, a senha deve conter no máximo 20 caracteres.")
	private String senha;
	private Perfil perfil;
	private LocalDate dataNascimento;
//	private List<Telefone> listaTelefone;

	public Usuario() {

	}

	public Usuario(Integer id, String nome, String login, String senha, Perfil perfil) {
		super();
		setId(id);
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

//	public List<Telefone> getListaTelefone() {
//		return listaTelefone;
//	}
//
//	public void setListaTelefone(List<Telefone> listaTelefone) {
//		this.listaTelefone = listaTelefone;
//	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}