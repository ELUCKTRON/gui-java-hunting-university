package hunting.ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {


    /**
     * making the frame for window application
     */
    public MyFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hunting");
        setSize(500,500);

        JPanel parentPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) parentPanel.getLayout();

        MainMenu mainMenu = new MainMenu(parentPanel,cardLayout);


        parentPanel.add(mainMenu,"MainMenu");

        this.add(parentPanel);
        setLocationRelativeTo(null);

    }

}
