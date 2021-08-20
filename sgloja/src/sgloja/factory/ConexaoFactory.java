package sgloja.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "123456";
	private static final String URL = "jdbc:mysql://localhost:3307/farmacia?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC&useSSL=false&autoReconnect=true&tcpKeepAlive=true&allowPublicKeyRetrieval=true";

	public static Connection conectar() throws SQLException {

		// Referencia ao Driver mysql para vers�es antigas do java
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectado com sucesso!!");
		}

		catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Conex�o falhou!!");
		}
	}

}