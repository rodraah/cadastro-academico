import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class Main {
	static TelaPrincipal main;

	public static List<String> cursos = new ArrayList<String>();
	public static Connection conexao;
	
	public static final String USUARIO_CERTO = "admin";
	public static final String SENHA_CERTA = "admin";

	public static void main(String[] args) {		
		conexao = Banco.obterConexao();
		
		// APAGAR!
		javax.swing.JFrame telaPrincipal = new TelaPrincipal(); telaPrincipal.setVisible(true);
		//new TelaLogin().setVisible(true);
	}
}