package br.unitins.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private ConnectionFactory( ) {
		// Construtor como privado para nao criar uma instancia
	}
	
	public static Connection getInstance() {
		Connection conn = null;
		
		try {
			// Registrando o driver do postgres
			Class.forName("org.postgresql.Driver");
			// Estabelecendo um conexao com o banco
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/cadastro",
															"postgres", "Tl784512");
		} catch (ClassNotFoundException e) {
			System.out.println("Falha ao registrar o Driver de banco.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Falha ao conectar ao banco de dados.");
			e.printStackTrace();
		}
		
		return conn;
	}

}
