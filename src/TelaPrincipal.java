import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame implements ActionListener {
 	JMenuBar barraMenu;
	JMenu menu1, menu2, menu3, menu4;
	JMenuItem mn1, mn2, mn3, mn4, mn5, mn6, mn7, mn8, mn13, mn14, mn15, mn16, mn17, mn18, mn19, mn20, mi13;
	
	public TelaPrincipal() {
		setSize(500,500);
		setResizable(false);
		setTitle("Sistema de Controle Acadêmico");
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		barraMenu = new JMenuBar();
		menu1 = new JMenu("Cadastrar");
		menu2 = new JMenu("Manutenção");
		menu3 = new JMenu("Mostrar");
		menu4 = new JMenu("Sair");

		mn1 = new JMenuItem("Curso");
		mn1.addActionListener(this);
		mn2 = new JMenuItem("Disciplina");
		mn2.addActionListener(this);
		mn3 = new JMenuItem("Aluno");
		mn3.addActionListener(this);
		mn4 = new JMenuItem("Professor");
		mn4.addActionListener(this);
		menu1.add(mn1);
		menu1.add(mn2);
		menu1.add(mn3);
		menu1.add(mn4);
		
		mn5 = new JMenuItem("Curso");
		mn5.addActionListener(this);
		mn6 = new JMenuItem("Disciplina");
		mn6.addActionListener(this);
		mn7 = new JMenuItem("Aluno");
		mn7.addActionListener(this);
		mn8 = new JMenuItem("Professor");
		mn8.addActionListener(this);
		menu2.add(mn5);
		menu2.add(mn6);
		menu2.add(mn7);
		menu2.add(mn8);
		
		mn13 = new JMenuItem("Professores por Especialização");
		mn13.addActionListener(this);
		mn14 = new JMenuItem("Cursos por tipo");
		mn14.addActionListener(this);
		mn15 = new JMenuItem("Alunos por disciplina");
		mn15.addActionListener(this);

		menu3.add(mn13);
		menu3.add(mn14);
		menu3.add(mn15);
		
		mn20 = new JMenuItem("Sair");
		mn20.addActionListener(this);
		menu4.add(mn20);
		
		barraMenu.add(menu1);
		barraMenu.add(menu2);
		barraMenu.add(menu3);
		barraMenu.add(menu4);
		
		setJMenuBar(barraMenu);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getActionCommand().equals("Sair")) {
			this.dispose(); 
		} else if (evento.getSource() == mn1) {
			new TelaCurso(this, 0);
		} else if (evento.getSource() == mn5) {
			new TelaCurso(this, 1);
		} else if (evento.getSource() == mn2) {
			new TelaDisciplina(this, 0);
		} else if (evento.getSource() == mn6) {
			new TelaDisciplina(this, 1);
		} else if (evento.getSource() == mn3) {
			new TelaAluno(this,0);
		} else if (evento.getSource() == mn7) {
			new TelaAluno(this, 1);
		} else if (evento.getSource() == mn4) {
			new TelaProf(this, 0);
		} else if (evento.getSource() == mn8) {
			new TelaProf(this, 1);
		} else if (evento.getActionCommand().equals("Professores por Especialização")) {
			String especializacao  = JOptionPane.showInputDialog("Digite a especialização: ");
			if (especializacao != null) {
				String query = String.format(
					"SELECT * FROM professor WHERE especializacao = \"%s\"", especializacao);
				try {
					ResultSet rs = Banco.select(query);
					String resultado = "";
					while (rs.next()) {
						resultado += rs.getString("nome");
						resultado += "\n";
					}
					if (resultado.length() >= 1)
						resultado = resultado.substring(0, resultado.length()-1);
					JOptionPane.showMessageDialog(this, String.format("Professores com a especialização %s: \n%s", especializacao, resultado));
				} catch (SQLException error) {
					error.printStackTrace();
				}
			}
		} else if (evento.getActionCommand().equals("Cursos por tipo")) {
			String tipo  = JOptionPane.showInputDialog("Digite o tipo do curso: ");
			if (tipo != null) {
				String query = String.format(
					"SELECT * FROM curso WHERE tipo_curso = \"%s\"", tipo);
				try {
					ResultSet rs = Banco.select(query);
					String resultado = "";
					while (rs.next()) {
						resultado += rs.getString("nome");
						resultado += "\n";
					}
					if (resultado.length() >= 1)
						resultado = resultado.substring(0, resultado.length()-1);
					JOptionPane.showMessageDialog(this, String.format("Cursos com o tipo %s: \n%s", tipo, resultado));
				} catch (SQLException error) {
					error.printStackTrace();
				}
			}
		} else if (evento.getActionCommand().equals("Alunos por disciplina")) {
			String disciplina  = JOptionPane.showInputDialog("Digite o nome da disciplina: ");
			if (disciplina != null) {
				String query = String.format(
					"SELECT aluno.nome FROM aluno INNER JOIN disciplina ON aluno.cod_disciplina = disciplina.cod WHERE disciplina.nome = \"%s\"", disciplina);
				try {
					ResultSet rs = Banco.select(query);
					String resultado = "";
					while (rs.next()) {
						resultado += rs.getString("nome");
						resultado += "\n";
					}
					if (resultado.length() >= 1)
						resultado = resultado.substring(0, resultado.length()-1);
					JOptionPane.showMessageDialog(this, String.format("Alunos na disciplina de %s: \n%s", disciplina, resultado));
				} catch (SQLException error) {
					error.printStackTrace();
				}
			}
		}
	}
}
