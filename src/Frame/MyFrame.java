package Frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JPanel {

    private JFrame frame = new JFrame("Messenger");

    public static MyFrame inst;

    public MyFrame() {
    }
    
    public static MyFrame getInst() {
        return inst;
    }

    public static void setInst(MyFrame inst) {
        MyFrame.inst = inst;
    }

    static public MyFrame getInstance() {
        if (inst == null) {
            inst = new MyFrame();
        }
        return inst;
    }

    public void changeFrame(JPanel jp) {
        frame.getContentPane().add(jp, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
