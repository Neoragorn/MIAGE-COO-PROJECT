package Frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JPanel {

    private JFrame frame = new JFrame("Messenger");

    private JPanel actualPanel = null;

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

    public void setActualPanel(JPanel actualPanel) {
        this.actualPanel = actualPanel;
    }

    public void startPoint(JPanel jp) {
        frame.getContentPane().add(jp, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.actualPanel = jp;
    }

    public void changeFrame(JPanel jp) {
        if (actualPanel != null) {
            actualPanel.removeAll();
        }
        frame.getContentPane().add(jp, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.actualPanel = jp;
    }
    
    public void quit() {
        frame.dispose();
    }
}
