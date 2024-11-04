import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TelaProf extends JDialog implements ActionListener {
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	JButton btIncluir, btExcluir, btAlterar, btSair;
	ButtonGroup gpRadio, gpRadio1;

	TelaProf(JFrame parent, int codigo) {
		super(parent, "Cadastrar professor");
		setVisible(true);
		setSize(650,500);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblTitulo = new JLabel("Manutenção de Professores");
		lblTitulo.setFont(new Font("Calibri", 1, 18));
		lblTitulo.setBounds(30,30,400,20);
		this.add(lblTitulo);
		
		// Coluna 1

		JLabel lbl1 = new JLabel("Identidade:");
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
		
		JLabel lbl3 = new JLabel("Endereço:");
		lbl3.setBounds(30,140,200,15);
		this.add(lbl3);

		tf3 = new JTextField();
		tf3.setBounds(200,140, 100,20);
		this.add(tf3);
		
		JLabel lbl4 = new JLabel("Data de nascimento:");
		lbl4.setBounds(30,170,200,15);
		this.add(lbl4);

		tf4 = new JTextField();
		tf4.setBounds(200,170, 100,20);
		this.add(tf4);
		
		JLabel lbl5 = new JLabel("Especialização:");
		lbl5.setBounds(30,200,200,15);
		this.add(lbl5);
		
		// Radio
		
		JRadioButton rb01 = new JRadioButton("Direito");
		rb01.setActionCommand("Direito");
		rb01.setBounds(30,230,120,15);
		this.add(rb01);
		
		JRadioButton rb02 = new JRadioButton("Informática");
		rb02.setActionCommand("Informática");
		rb02.setBounds(150,230,120,15);
		this.add(rb02);
		
		JRadioButton rb03 = new JRadioButton("Matematica");
		rb03.setActionCommand("Matematica");
		rb03.setBounds(30,260,120,15);
		this.add(rb03);
		
		JRadioButton rb04 = new JRadioButton("Medicina");
		rb04.setActionCommand("Medicina");
		rb04.setBounds(150,260,120,15);
		this.add(rb04);
		
		gpRadio = new ButtonGroup();
		gpRadio.add(rb01);
		gpRadio.add(rb02);
		gpRadio.add(rb03);
		gpRadio.add(rb04);
		
		
		// Radio 01

		JLabel lbl6 = new JLabel("Titulo Professor:");
		lbl6.setBounds(30,290,200,15);
		this.add(lbl6);
		
		JRadioButton rb05 = new JRadioButton("Bacharel");
		rb05.setActionCommand("Bacharel");
		rb05.setBounds(30,320,120,15);
		this.add(rb05);
		
		JRadioButton rb06 = new JRadioButton("Especialista Latu Sensu");
		rb06.setActionCommand("Especialista Latu Sensu");
		rb06.setBounds(150,320,170,15);
		this.add(rb06);
		
		JRadioButton rb07 = new JRadioButton("Mestrado");
		rb07.setActionCommand("Mestrado");
		rb07.setBounds(30,350,120,15);
		this.add(rb07);
		
		JRadioButton rb08 = new JRadioButton("Doutorado");
		rb08.setActionCommand("Doutorado");
		rb08.setBounds(150,350,120,15);
		this.add(rb08);
		
		gpRadio1 = new ButtonGroup();
		gpRadio1.add(rb05);
		gpRadio1.add(rb06);
		gpRadio1.add(rb07);
		gpRadio1.add(rb08);
	
		// Botões

		btIncluir = new JButton("Incluir");
		btIncluir.setBounds(40,400,125,40);
		btIncluir.addActionListener(this);
		this.add(btIncluir);

		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(180,400,125,40);
		btExcluir.addActionListener(this);
		this.add(btExcluir);

		btAlterar = new JButton("Alterar");
		btAlterar.setBounds(320,400,125,40);
		btAlterar.addActionListener(this);
		this.add(btAlterar);
		
		btSair = new JButton("Sair");
		btSair.setBounds(460,400,125,40);
		btSair.addActionListener(this);
		this.add(btSair);
		
		// Código - 1 para somente inclusão, 0 para modificação
		if (codigo == 0) {
			lblTitulo.setText("Cadastro de Professores");
			btExcluir.setVisible(false);
			btAlterar.setVisible(false);
		} else if (codigo == 1) {
			btIncluir.setVisible(false);
		}
	}
	
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand().equals("Sair")) {
			this.dispose();
		}
		if (e.getActionCommand().equals("Incluir")) {
			String especializacao = gpRadio.getSelection().getActionCommand();
			String titulo = gpRadio1.getSelection().getActionCommand();
			String query = String.format(
				"INSERT INTO professor VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), especializacao, titulo);
			if (Banco.update(query)) {
				JOptionPane.showMessageDialog(this, "Incluído!");
				limpar();
			}
		} else if(e.getActionCommand().equals("Excluir")) {
			if (preencherDoBanco()) {
				btExcluir.setText("Confirmar");
			}
		} else if(e.getActionCommand().equals("Alterar")) {
			if (preencherDoBanco()) {
				btAlterar.setText("Confirmar");
			}
		} else if(e.getSource().equals(btExcluir)) {
			String query = String.format("DELETE FROM professor WHERE cod = %s", tf1.getText());
			if (Banco.update(query)) {
				JOptionPane.showMessageDialog(this, "Excluído!");
				btExcluir.setText("Excluir");
				limpar();
			}
		} else if(e.getSource().equals(btAlterar)) {
			String especializacao = gpRadio.getSelection().getActionCommand();
			String titulo = gpRadio1.getSelection().getActionCommand();
			String query = String.format(
				"UPDATE professor SET nome = \"%s\", endereco = \"%s\", dt_nasc = \"%s\", especializacao = \"%s\", titulo = \"%s\" WHERE cod = \"%s\"", tf2.getText(), tf3.getText(), tf4.getText(), especializacao, titulo, tf1.getText());
			if (Banco.update(query)) {
				JOptionPane.showMessageDialog(this, "Alterado!");
				btAlterar.setText("Alterar");
				limpar();
			}
		}
	}

	public void limpar() {
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		gpRadio.clearSelection();
		gpRadio1.clearSelection();
	}

	public boolean preencherDoBanco() {
		String query = String.format("SELECT * FROM professor WHERE cod = %s", tf1.getText());
		ResultSet rs = Banco.select(query);
		try {
			rs.next();
			tf2.setText(rs.getString("nome"));
			tf3.setText(rs.getString("endereco"));
			tf4.setText(rs.getString("dt_nasc"));
			Utilitarios.setButtonGroup(rs.getString("especializacao"), gpRadio);
			Utilitarios.setButtonGroup(rs.getString("titulo"), gpRadio1);
			return true;
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			if (errorMessage.startsWith("Illegal operation on empty result set.")) {
				JOptionPane.showMessageDialog(this, "Código " + tf1.getText() + " não encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
			}
			return false;
		}
	}
}