package expenses.view;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    Font myFont = new Font("Arial", Font.BOLD, 15);

    public MyFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Expenses manager");
        this.setLocationRelativeTo(null);
        //skuska


        this.setVisible(true);
    }
}
