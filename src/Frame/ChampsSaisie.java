package Frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChampsSaisie extends JPanel {
	
	JButton validation;
	static JTextField TF1;
	static JTextField TF2;
	static JTextField TF3;
	
	public ChampsSaisie(){
		setLayout(null);
		setPreferredSize(new Dimension(500, 300));
		validation = new JButton("Valider");
		TF1 = new JTextField("Pseudo");
		TF2 = new JTextField("Mail");
		TF3 = new JTextField("Password");
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setOpaque(false);
		
		p1.add(validation);
		validation.setBounds(300, 270, 50, 20);
		
		TF1.setBackground(new Color(100,100,100));
		TF1.setForeground(new Color(255,255,255));
		
		p1.add(TF1);
		p1.add(TF2);
		p1.add(TF3);

		TF1.setBounds(200, 50, 200, 30);
		TF2.setBounds(200, 100, 200, 30);
		TF3.setBounds(200, 150, 200, 30);

		
		p1.setBounds(0, 0, 500, 300);
		add(p1);
	}
	
}
