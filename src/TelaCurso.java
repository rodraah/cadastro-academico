import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TelaCurso extends JDialog implements ActionListener {
	JTextField tf0, tf1, tf2, tf3;
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

		JButton btIncluir = new JButton("Incluir");
		btIncluir.setBounds(40,400,150,40);
		btIncluir.addActionListener(this);
		this.add(btIncluir);

		JButton btLimpar = new JButton("Limpar");
		btLimpar.setBounds(220,400,150,40);
		btLimpar.addActionListener(this);
		this.add(btLimpar);

		JButton brSair = new JButton("Sair");
		brSair.setBounds(400,400,150,40);
		brSair.addActionListener(this);
		this.add(brSair);
	}
	
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand().equals("Sair")) {
			this.dispose();
		} 
		if (e.getActionCommand().equals("Incluir")) {
			String tipoCurso = gpRadio.getSelection().getActionCommand();
			String query = String.format(
					"INSERT INTO Curso VALUES(\"%s\",\"%s\", \"%s\",\"%s\",\"%s\")", tf0.getText(),tf1.getText(),tf2.getText(),tf3.getText(),tipoCurso);
			Banco.update(query);
		}
	}
}