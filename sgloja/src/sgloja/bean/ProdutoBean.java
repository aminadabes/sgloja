package sgloja.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sgloja.DAO.FornecedorDAO;
import sgloja.DAO.ProdutoDAO;
import sgloja.domain.Fornecedor;
import sgloja.domain.Produto;
import sgloja.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private Produto produto;
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	private ArrayList<Fornecedor> comboFornecedor;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(ArrayList<Fornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {

		try {
			produto = new Produto();
			FornecedorDAO dao = new FornecedorDAO();
			comboFornecedor = dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void novo() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(produto);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produto);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.editar(produto);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararEditar() {

		try {
			produto = new Produto();
			FornecedorDAO dao = new FornecedorDAO();
			comboFornecedor = dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}