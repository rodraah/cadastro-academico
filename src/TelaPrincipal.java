import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		mn13 = new JMenuItem("Curso");
		mn13.addActionListener(this);
		mn14 = new JMenuItem("Disciplina");
		mn14.addActionListener(this);
		mn15 = new JMenuItem("Aluno");
		mn15.addActionListener(this);
		mn16 = new JMenuItem("Professor/Cursos/Professores");
		mn16.addActionListener(this);
		mn17 = new JMenuItem("Cursos/Disciplinas");
		mn17.addActionListener(this);
		mn18 = new JMenuItem("Professores/Disciplinas");
		mn18.addActionListener(this);
		mn19 = new JMenuItem("Aluno/Disciplina");
		mn19.addActionListener(this);
		menu3.add(mn13);
		menu3.add(mn14);
		menu3.add(mn15);
		menu3.addSeparator();
		menu3.add(mn16);
		menu3.add(mn17);
		menu3.add(mn18);
		menu3.add(mn19);
		
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
		} else if (evento.getSource() == mn1 || evento.getSource() == mn5) {
			new TelaCurso(this);
		} else if (evento.getSource() == mn2 || evento.getSource() == mn6) {
			new TelaDisciplina(this);
		} else if (evento.getSource() == mn3 || evento.getSource() == mn7) {
			new TelaAluno(this,0);
		} else if (evento.getSource() == mn4 || evento.getSource() == mn8) {
			new TelaProf(this);
		} else if (evento.getActionCommand().equals("Curso")) {
			JOptionPane.showMessageDialog(this, String.format("Cursos: \n%s", Main.cursos));
		}else if (evento.getSource() instanceof JMenuItem) {
			JOptionPane.showMessageDialog(this, evento.getActionCommand());
		}
	}
}
