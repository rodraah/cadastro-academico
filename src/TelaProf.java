import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaProf extends JDialog implements ActionListener {
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;

	TelaProf(JFrame parent) {
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
		
		// Checkbox
		
		JCheckBox checkbox1 = new JCheckBox("Direito");
		checkbox1.setBounds(30,230,120,15);
		this.add(checkbox1);
		
		JCheckBox checkbox2 = new JCheckBox("Informática");
		checkbox2.setBounds(150,230,120,15);
		this.add(checkbox2);
		
		JCheckBox checkbox3 = new JCheckBox("Matematica");
		checkbox3.setBounds(30,260,120,15);
		this.add(checkbox3);
		
		JCheckBox checkbox4 = new JCheckBox("Medicina");
		checkbox4.setBounds(150,260,120,15);
		this.add(checkbox4);
		
		
		JLabel lbl6 = new JLabel("Titulo Professor:");
		lbl6.setBounds(30,290,200,15);
		this.add(lbl6);
		
		// Checkbox
		
		JCheckBox checkbox5 = new JCheckBox("Bacharel");
		checkbox5.setBounds(30,320,120,15);
		this.add(checkbox5);
		
		JCheckBox checkbox6 = new JCheckBox("Especialista Latu Sensu");
		checkbox6.setBounds(150,320,170,15);
		this.add(checkbox6);
		
		JCheckBox checkbox7 = new JCheckBox("Mestrado");
		checkbox7.setBounds(30,350,120,15);
		this.add(checkbox7);
		
		JCheckBox checkbox8 = new JCheckBox("Doutorado");
		checkbox8.setBounds(150,350,120,15);
		this.add(checkbox8);
		
	
		// Bot�es

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
	}
}