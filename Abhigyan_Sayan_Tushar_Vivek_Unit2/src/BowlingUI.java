import javax.swing.JFrame;

public class BowlingUI {

    public int run() {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(10, 10, 200, 800);
        obj.setTitle("Simple Bowling");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.add(gameplay);
    }
}