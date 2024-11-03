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

public class TelaDisciplina extends JDialog implements ActionListener {
	JTextField tf1, tf2, tf3;
	JButton btIncluir, btExcluir, btAlterar, btSair;
	ButtonGroup gpRadio;

	TelaDisciplina(JFrame parent) {
		super(parent, "Cadastrar disciplina");
		setVisible(true);
		setSize(650,500);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblTitulo = new JLabel("Manutenção de Disciplinas");
		lblTitulo.setFont(new Font("Calibri", 1, 18));
		lblTitulo.setBounds(30,30,400,20);
		this.add(lblTitulo);
		
		// Coluna 1

		JLabel lbl1 = new JLabel("Cód da disciplina:");
		lbl1.setBounds(30,80,200,15);
		this.add(lbl1);

		tf1 = new JTextField();
		tf1.setBounds(200,80, 100,20);
		this.add(tf1);
		
		JLabel lbl2 = new JLabel("Nome da disciplina:");
		lbl2.setBounds(30,110,200,15);
		this.add(lbl2);

		tf2 = new JTextField();
		tf2.setBounds(200,110, 100,20);
		this.add(tf2);
		
		JLabel lbl3 = new JLabel("Carga horária:");
		lbl3.setBounds(30,140,200,15);
		this.add(lbl3);

		tf3 = new JTextField();
		tf3.setBounds(200,140, 100,20);
		this.add(tf3);
		
		JLabel lbl4 = new JLabel("Aulas p/ Semana:");
		lbl4.setBounds(30,170,200,15);
		this.add(lbl4);

		JRadioButton radio1 = new JRadioButton("1"); 
		radio1.setActionCommand("1");
		radio1.setBounds(30,200,40,15);
		this.add(radio1);

		JRadioButton radio2 = new JRadioButton("2"); 
		radio2.setActionCommand("2");
		radio2.setBounds(30,230,40,15);
		this.add(radio2);

		JRadioButton radio3 = new JRadioButton("3"); 
		radio3.setActionCommand("3");
		radio3.setBounds(30,260,40,15);
		this.add(radio3);
		
		JRadioButton radio4 = new JRadioButton("4"); 
		radio4.setActionCommand("4");
		radio4.setBounds(100,200,40,15);
		this.add(radio4);
		
		JRadioButton radio5 = new JRadioButton("5"); 
		radio5.setActionCommand("5");
		radio5.setBounds(100,230,40,15);
		this.add(radio5);

		JRadioButton radio6 = new JRadioButton("6"); 
		radio6.setActionCommand("6");
		radio6.setBounds(100,260,40,15);
		this.add(radio6);
		
		gpRadio = new ButtonGroup();
		gpRadio.add(radio1);
		gpRadio.add(radio2);
		gpRadio.add(radio3);
		gpRadio.add(radio4);
		gpRadio.add(radio5);
		gpRadio.add(radio6);

		// Botões

		btIncluir = new JButton("Incluir");
		btIncluir.setBounds(40,350,125,40);
		btIncluir.addActionListener(this);
		this.add(btIncluir);

		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(180,350,125,40);
		btExcluir.addActionListener(this);
		this.add(btExcluir);

		btAlterar = new JButton("Alterar");
		btAlterar.setBounds(320,350,125,40);
		btAlterar.addActionListener(this);
		this.add(btAlterar);
		
		btSair = new JButton("Sair");
		btSair.setBounds(460,350,125,40);
		btSair.addActionListener(this);
		this.add(btSair);
	}
	
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand().equals("Sair")) {
			this.dispose();
		}
		if (e.getActionCommand().equals("Incluir")) {
			String aulas = gpRadio.getSelection().getActionCommand();
			String query = String.format(
				"INSERT INTO disciplina VALUES(\"%s\", \"%s\", \"%s\", \"%s\")", tf1.getText(), tf2.getText(), tf3.getText(), aulas); 
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
			String query = String.format("DELETE FROM disciplina WHERE cod = %s", tf1.getText());
			if (Banco.update(query)) {
				JOptionPane.showMessageDialog(this, "Excluído!");
				btExcluir.setText("Excluir");
				limpar();
			}
		} else if(e.getSource().equals(btAlterar)) {
			String aulas = gpRadio.getSelection().getActionCommand();
			String query = String.format(
				"UPDATE disciplina SET nome = \"%s\", carga = \"%s\", aulas_semana = \"%s\" WHERE cod = %s", tf2.getText(), tf3.getText(), aulas, tf1.getText());
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
		gpRadio.clearSelection();
	}

	public boolean preencherDoBanco() {
		String query = String.format("SELECT * FROM disciplina WHERE cod = %s", tf1.getText());
		ResultSet rs = Banco.select(query);
		try {
			rs.next();
			tf2.setText(rs.getString("nome"));
			tf3.setText(rs.getString("carga"));
			Utilitarios.setButtonGroup(rs.getString("aulas_semana"), gpRadio);
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
