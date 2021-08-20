package sgloja.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sgloja.domain.Fornecedor;
import sgloja.factory.ConexaoFactory;

public class FornecedorDAO {
	public void salvar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();

	}
	
	public void excluir (Fornecedor f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	public void editar (Fornecedor f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	public Fornecedor buscaPorCodigo(Fornecedor f)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedor retorno = null;
		
		if(resultado.next()){
			retorno = new Fornecedor();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	
	public ArrayList<Fornecedor>buscarPorDescricao(Fornecedor f)throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Fornecedor>lista = new ArrayList<Fornecedor>();
		
		while(resultado.next()){
			Fornecedor item = new Fornecedor();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}

		return lista;
	}
	
	
	public ArrayList<Fornecedor> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
				
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Fornecedor>lista = new ArrayList<Fornecedor>();
		
		while(resultado.next()){
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}

		return lista;
	}
	
   
	public static void main(String[] args) {
		
		
		 Fornecedor f1 = new Fornecedor();
		f1.setDescricao("ROBERTO");
		
		Fornecedor f2 = new Fornecedor();
		f2.setDescricao("VALDIR");
		
		FornecedorDAO fdao = new FornecedorDAO();
		
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			System.out.println("Salvo com sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Erro ao salvar");
			e.printStackTrace();
		}   
		
		
	/*	Fornecedores f1 = new Fornecedores();
		f1.setCodigo(3L);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.excluir(f1);
			
			System.out.println("Deletado com sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar");
			e.printStackTrace();
		}   */
		
		/*
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
		f1.setDescricao("HUGO VASCONCELOS");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.editar(f1);
			
			System.out.println("Editado com sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Erro ao Editar");
			e.printStackTrace();
		}  */
		
		
		/*
		 Fornecedores f1 = new Fornecedores();
			f1.setCodigo(2);
			
			Fornecedores f2 = new Fornecedores();
			f2.setCodigo(3);
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try {
				Fornecedores f3 = fdao.buscaPorCodigo(f1);
				Fornecedores f4 = fdao.buscaPorCodigo(f2);
				
				System.out.println("Resultado 1: " + f3);
				System.out.println("Resultado 2: " + f4);
				
			} catch (SQLException e) {
				System.out.println("Erro ao buscar");
				e.printStackTrace();
			}   
		*/
		
		/*
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			
			ArrayList<Fornecedores>lista = fdao.listar();
			
			for (Fornecedores f : lista){
			System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar");
			e.printStackTrace();
		}   
		*/
		
		
		/*Fornecedores f1 = new Fornecedores();
			f1.setDescricao("de");
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try {
				
				
				
				ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
				
				for (Fornecedores f : lista){
				System.out.println("Resultado " + f);
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao buscar");
				e.printStackTrace();
			}  */ 
			
	}
}