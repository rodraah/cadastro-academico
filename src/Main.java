import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

public class Main {
	static TelaPrincipal main;

	public static List<String> cursos = new ArrayList<String>();
	public static Connection conexao;
	
	public static final String USUARIO_CERTO = "admin";
	public static final String SENHA_CERTA = "admin";

	public static void main(String[] args) {		
		conexao = Banco.obterConexao();
		
		new TelaLogin().setVisible(true);
	}
}