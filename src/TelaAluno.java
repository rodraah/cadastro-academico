import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class TelaAluno extends JDialog implements ActionListener {
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf8;
	JLabel tlMedia;

	TelaAluno(JFrame parent, int codigo) {
		super(parent, "Cadastrar aluno");
		setVisible(true);
		setSize(650,500);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblTitulo = new JLabel("Manutenção de Alunos");
		lblTitulo.setFont(new Font("Calibri", 1, 18));
		lblTitulo.setBounds(30,30,400,20);
		this.add(lblTitulo);
		
		// Coluna 1

		JLabel lbl1 = new JLabel("Matrícula:");
		lbl1.setBounds(30,80,200,15);
		this.add(lbl1);

		tf1 = new JTextField();
		tf1.setBounds(200,80, 100,20);
		this.add(tf1);
		
		JLabel lbl2 = new JLabel("Nome:");
		lbl2.setBounds(30,110,200,15);
		this.add(lbl2);

		tf2 = new JTextField();
		tf2.setBounds(200,110, 100,20);
		this.add(tf2);
		
		JLabel lbl3 = new JLabel("Data de nascimento:");
		lbl3.setBounds(30,140,200,15);
		this.add(lbl3);

		tf3 = new JTextField();
		tf3.setBounds(200,140, 100,20);
		this.add(tf3);
		
		JLabel lbl4 = new JLabel("Código do curso:");
		lbl4.setBounds(30,170,200,15);
		this.add(lbl4);

		tf4 = new JTextField();
		tf4.setBounds(200,170, 100,20);
		this.add(tf4);
		
		JLabel lbl5 = new JLabel("Nota da NP1:");
		lbl5.setBounds(30,200,200,15);
		this.add(lbl5);

		tf5 = new JTextField();
		tf5.setBounds(200,200, 100,20);
		this.add(tf5);
		
		JLabel lbl6 = new JLabel("Nota da NP2:");
		lbl6.setBounds(30,230,200,15);
		this.add(lbl6);

		tf6 = new JTextField();
		tf6.setBounds(200,230, 100,20);
		this.add(tf6);
		
		JLabel lbl7 = new JLabel("Média:");
		lbl7.setBounds(30,260,200,15);
		this.add(lbl7);

		tlMedia = new JLabel();
		tlMedia.setBounds(200,260, 100,20);
		this.add(tlMedia);
		
		JLabel lbl8 = new JLabel("Faltas:");
		lbl8.setBounds(30,290,200,15);
		this.add(lbl8);

		tf8 = new JTextField();
		tf8.setBounds(200,290, 100,20);
		this.add(tf8);

		// Coluna 2

		JLabel lblNomeCurso = new JLabel("Disciplina: ");
		lblNomeCurso.setBounds(350, 80, 150, 15);
		this.add(lblNomeCurso);

		DefaultListModel<String> listaCurso = new DefaultListModel<String>();
		ResultSet rsDisciplinas = Banco.select(
				"SELECT * FROM disciplina");
		
		try {
			while (rsDisciplinas.next()) {
				listaCurso.addElement(rsDisciplinas.getString("cod") + " - " + rsDisciplinas.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JList<String> lista = new JList<String>(listaCurso);
		lista.setBounds(350, 110, 200,175);
		this.add(lista);

		// Botões

		JButton btIncluir = new JButton("Incluir");
		btIncluir.setBounds(40,350,125,40);
		btIncluir.addActionListener(this);
		this.add(btIncluir);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.setBounds(180,350,125,40);
		btExcluir.addActionListener(this);
		this.add(btExcluir);

		JButton brAlterar = new JButton("Alterar");
		brAlterar.setBounds(320,350,125,40);
		brAlterar.addActionListener(this);
		this.add(brAlterar);
		
		JButton brSair = new JButton("Sair");
		brSair.setBounds(460,350,125,40);
		brSair.addActionListener(this);
		this.add(brSair);
	}
	
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand().equals("Sair")) {
			this.dispose();
		}
	}
}
