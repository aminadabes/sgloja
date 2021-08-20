package sgloja.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sgloja.domain.Contato;
import sgloja.factory.ConexaoFactory;

public class ContatoDAO {
	public void salvar(Contato f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO contato ");
		sql.append("(nome) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.executeUpdate();

	}
	
	public void excluir (Contato f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM contato ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getId());
		comando.executeUpdate();
		
	}
	
	
	public void editar (Contato f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE contato ");
		sql.append("SET nome = ? ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getNome());
		comando.setLong(2, f.getId());
		comando.executeUpdate();
		
	}
	
	
	public Contato buscaPorCodigo(Contato f)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM contato ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		comando.setLong(1, f.getId());
		
		ResultSet resultado = comando.executeQuery();
		Contato retorno = null;
		
		if(resultado.next()){
			retorno = new Contato();
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
		}
		
		return retorno;
	}
	
	
	public ArrayList<Contato>buscarPorDescricao(Contato f)throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM contato ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getNome() + "%");
		
		
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Contato>lista = new ArrayList<Contato>();
		
		while(resultado.next()){
			Contato item = new Contato();
			item.setId(resultado.getLong("id"));
			item.setNome(resultado.getString("nome"));
			
			lista.add(item);
		}

		return lista;
	}
	
	
	public ArrayList<Contato> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM contato ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
				
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Contato>lista = new ArrayList<Contato>();
		
		while(resultado.next()){
			Contato f = new Contato();
			f.setId(resultado.getLong("id"));
			f.setNome(resultado.getString("nome"));
			
			lista.add(f);
		}

		return lista;
	}
	
   
	public static void main(String[] args) {
		
		
		Contato f1 = new Contato();
		f1.setNome("ROBERTO");
		
		Contato f2 = new Contato();
		f2.setNome("VALDIR");
		
		ContatoDAO fdao = new ContatoDAO();
		
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