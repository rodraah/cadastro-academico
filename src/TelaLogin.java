import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame implements ActionListener {
	JTextField tf1, tf2;
	JButton btOk, btSair;
	int tentativas = 0;
	
	public TelaLogin() {
		setSize(300, 200);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login");
		
		JLabel lbl1 = new JLabel("Login: ");
		lbl1.setBounds(50, 30, 100, 20);
		add(lbl1);
		
		tf1 = new JTextField();
		tf1.setBounds(130,30,100,25);
		add(tf1);
		
		JLabel lbl2 = new JLabel("Senha: ");
		lbl2.setBounds(50, 70, 100, 20);
		add(lbl2);
		
		tf2 = new JPasswordField();
		tf2.setBounds(130,70,100,25);
		add(tf2);
		
		btOk = new JButton("OK");
		btOk.addActionListener(this);
		btOk.setBounds(50, 110, 80, 30);
		add(btOk);
		
		btSair = new JButton("Sair");
		btSair.addActionListener(this);
		btSair.setBounds(150, 110, 80, 30);
		add(btSair);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btOk) {
			String usuario = tf1.getText();
			String senha = tf2.getText();
			
			if ((usuario.equals(Main.USUARIO_CERTO)) && (senha.equals(Main.SENHA_CERTA))) {
				new TelaPrincipal().setVisible(true);
				this.dispose();
			} else {
				if (tentativas < 2) {
					tentativas += 1;
					JOptionPane.showMessageDialog(this, String.format("O usuário ou senha digitados estão incorretos\nVocê ainda tem %d tentativas", 3-tentativas));
				} else {
					JOptionPane.showMessageDialog(this, "ERROUUUUU");
					this.dispose();
				}
			}
		} else if (e.getSource() == btSair) {
			this.dispose();
		}
	}
}