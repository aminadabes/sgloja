package sgloja.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sgloja.DAO.ContatoDAO;
import sgloja.domain.Contato;
import sgloja.util.JSFUtil;

@ManagedBean(name = "MBContatos")
@ViewScoped
public class ContatoBean {

	private Contato contato;
	private ArrayList<Contato> itens;
	private ArrayList<Contato> itensFiltrados;

	public Contato getContato() {
		return contato;
	}

	public void setFornecedor(Contato contato) {
		this.contato = contato;
	}

	public ArrayList<Contato> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Contato> itens) {
		this.itens = itens;
	}

	public ArrayList<Contato> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Contato> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ContatoDAO fdao = new ContatoDAO();
			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {
		contato = new Contato();
	}

	public void novo() {

		try {
			ContatoDAO fdao = new ContatoDAO();
			fdao.salvar(contato);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Contato salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ContatoDAO fdao = new ContatoDAO();
			fdao.excluir(contato);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Contato excluido com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um Contato que tenha um produto associado!");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ContatoDAO fdao = new ContatoDAO();
			fdao.editar(contato);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Contato editado com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}