package Frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JTextPane;

import Bean.UserBean;

public class Home extends JPanel implements ActionListener{

    JButton quitter;

    public Home(){
    	setLayout(null);
        setPreferredSize(new Dimension(500, 300));
    	JPanel p1 = new JPanel();
    	p1.setLayout(null);
        p1.setOpaque(false);
    	quitter = new JButton("Quit");
    	quitter.setBounds(50, 200, 150, 20);
        quitter.addActionListener(this);
        
    	p1.add(quitter);
    	
        
        p1.setBounds(0, 0, 500, 300);
        add(p1);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quit")) {
            MyFrame.getInstance().quit();
        }
    }

}
