package br.com.restaurante.funcionarios.connection;

import java.sql.*;

public class ConnectionSQL {
	
		private static final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		private static final String user = "rm552781";
		private static final String password = "fiap25";
		
		public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url, user, password);
		}
		public static void main(String[] args) {

		try {
			Connection conn = ConnectionSQL.getConnection();
		    if (conn != null) {
		        System.out.println("Conexão estabelecida com sucesso!");
		    } else {
		        System.out.println("Falha ao estabelecer a conexão!");
		    }
		} catch (SQLException e) {
		    System.out.println("Erro ao conectar ao banco: ");
		    System.out.println("Mensagem de erro: " + e.getMessage());
		    System.out.println("Código de erro: " + e.getErrorCode());
		    System.out.println("SQL state: " + e.getSQLState());
		    e.printStackTrace();
		}}

	}