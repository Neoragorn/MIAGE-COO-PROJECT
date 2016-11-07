package Frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonListener;

public class ChampsSaisie extends JPanel implements ActionListener{
	
	JButton validation;
	static JTextField TFPseudo;
	static JTextField TFMail;
	static JTextField TFPassword;
	static String Pseudo;
	static String Mail;
	static String Password;
	
	public ChampsSaisie(){
		setLayout(null);
		setPreferredSize(new Dimension(500, 300));
		validation = new JButton("Valider");
		TFPseudo = new JTextField("Pseudo");
		TFMail = new JTextField("Mail");
		TFPassword = new JTextField("Password");
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setOpaque(false);
		
		p1.add(validation);
		validation.setBounds(300, 270, 50, 20);		
		validation.addActionListener(this);
		
		TFPseudo.setBackground(new Color(100,100,100));
		TFPseudo.setForeground(new Color(255,255,255));
		
		p1.add(TFPseudo);
		p1.add(TFMail);
		p1.add(TFPassword);

		TFPseudo.setBounds(200, 50, 200, 30);
		TFMail.setBounds(200, 100, 200, 30);
		TFPassword.setBounds(200, 150, 200, 30);

		
		p1.setBounds(0, 0, 500, 300);
		add(p1);
	}
	
	public void actionPerformed(ActionEvent e) {
		Pseudo = TFPseudo.getText();
		Mail = TFMail.getText();
		Password = TFPassword.getText();
		ArrayList info = new ArrayList<String>();
		info.add(Pseudo);
		info.add(Mail);
		info.add(Password);
	}
	
	public ArrayList getInfo(){
		return getInfo();
	}
}
