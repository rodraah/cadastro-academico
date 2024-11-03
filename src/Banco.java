import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

class Banco {
	private static Connection connection = null;
	
	public static void fazerConexao() {
		String url = "jdbc:mysql://localhost/cadastro_academico?user=root";
		String user = "root";
		String pass = "mysql";
		
		System.out.println("Tentando conexão com o banco...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver carregado.");
			
			connection = DriverManager.getConnection(url,user,pass);
			System.out.println("Conexão estabelecida.");
		} catch (ClassNotFoundException e) {
			System.err.println("Falha na conexão com o banco");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.err.println("Banco não disponível");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static Connection obterConexao() {
		if (connection == null) {
			fazerConexao();
			return connection;
		}
		return connection;
	}
	
	public static ResultSet select(String query) {
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean update(String query) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")){
				JOptionPane.showMessageDialog(null, "Registro duplicado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
			}
			return false;
		}
	}
}