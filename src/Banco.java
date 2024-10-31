import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Banco {
	private static Connection connection = null;
	
	public static void fazerConexao() {
		String url = "jdbc:mysql://localhost/cadastro_academico?user=root";
		String user = "root";
		String pass = "mysql";
		
		System.out.println("Tentando conex�o com o banco...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver carregado.");
			
			connection = DriverManager.getConnection(url,user,pass);
			System.out.println("Conex�o estabelecida.");
		} catch (ClassNotFoundException e) {
			System.err.println("Falha na conex�o com o banco");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.err.println("Banco n�o dispon�vel");
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
	
	public static void update(String query) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}