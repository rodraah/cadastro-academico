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

public class TelaCurso extends JDialog implements ActionListener {
	JTextField tf0, tf1, tf2, tf3;
	JButton btIncluir, btExcluir, btAlterar, btSair;
	ButtonGroup gpRadio;

	TelaCurso(JFrame parent) {
		super(parent, "Cadastrar curso");
		setVisible(true);
		setSize(650,500);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblTitulo = new JLabel("Manutenção de Cursos");
		lblTitulo.setFont(new Font("Calibri", 1, 18));
		lblTitulo.setBounds(30,30,400,20);
		this.add(lblTitulo);
		
		// Coluna 1

		JLabel lbl0 = new JLabel("Nome do curso:");
		lbl0.setBounds(30, 80, 200, 15);
		this.add(lbl0);
		
		tf0 = new JTextField();
		tf0.setBounds(200, 80, 100, 20);
		this.add(tf0);
		
		JLabel lbl1 = new JLabel("Código do curso:");
		lbl1.setBounds(30,110,200,15);
		this.add(lbl1);

		tf1 = new JTextField();
		tf1.setBounds(200,110, 100,20);
		this.add(tf1);
		
		JLabel lbl2 = new JLabel("Carga Horária:");
		lbl2.setBounds(30,140,200,15);
		this.add(lbl2);

		tf2 = new JTextField();
		tf2.setBounds(200,140, 100,20);
		this.add(tf2);
		
		JLabel lbl3 = new JLabel("Código do Instituto:");
		lbl3.setBounds(30,170,200,15);
		this.add(lbl3);

		tf3 = new JTextField();
		tf3.setBounds(200,170, 100,20);
		this.add(tf3);
		
		JLabel lbl4 = new JLabel("Tipo do Curso:");
		lbl4.setBounds(30,200,200,15);
		this.add(lbl4);

		JRadioButton radioBacharel = new JRadioButton("Bacharel");
		radioBacharel.setActionCommand("Bacharel");
		radioBacharel.setBounds(30,230,200,15);
		this.add(radioBacharel);

		JRadioButton radioGestao = new JRadioButton("Gestão");
		radioGestao.setActionCommand("Gestão");
		radioGestao.setBounds(30,260,200,15);
		this.add(radioGestao);

		JRadioButton radioOutros = new JRadioButton("Outros");
		radioOutros.setActionCommand("Outros");
		radioOutros.setBounds(30,290,200,15);
		this.add(radioOutros);
		
		gpRadio = new ButtonGroup();
		gpRadio.add(radioBacharel);
		gpRadio.add(radioGestao);
		gpRadio.add(radioOutros);

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
			String tipoCurso = gpRadio.getSelection().getActionCommand();
			String query = String.format(
					"INSERT INTO curso VALUES(\"%s\",\"%s\", \"%s\",\"%s\",\"%s\")", tf0.getText(),tf1.getText(),tf2.getText(),tf3.getText(),tipoCurso);
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
			String query = String.format("DELETE FROM curso WHERE cod = %s", tf1.getText());
			if (Banco.update(query)) {
				JOptionPane.showMessageDialog(this, "Excluído!");
				btExcluir.setText("Excluir");
				limpar();
			}
		} else if(e.getSource().equals(btAlterar)) {
			String tipoCurso = gpRadio.getSelection().getActionCommand();
			String query = String.format(
				"UPDATE curso SET nome = \"%s\", carga = \"%s\", cod_inst = \"%s\", tipo_curso = \"%s\" WHERE cod = %s", tf0.getText(), tf2.getText(), tf3.getText(), tipoCurso, tf1.getText());
			if (Banco.update(query)) {
				JOptionPane.showMessageDialog(this, "Alterado!");
				btAlterar.setText("Alterar");
				limpar();
			}
		}
	}

	public void limpar() {
		tf0.setText("");
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		gpRadio.clearSelection();
	}

	public boolean preencherDoBanco() {
		String query = String.format("SELECT nome, carga, cod_inst, tipo_curso FROM curso WHERE cod = %s", tf1.getText());
		ResultSet rs = Banco.select(query);
		try {
			rs.next();
			tf0.setText(rs.getString("nome"));
			tf2.setText(rs.getString("carga"));
			tf3.setText(rs.getString("cod_inst"));
			Utilitarios.setButtonGroup(rs.getString("tipo_curso"), gpRadio);
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