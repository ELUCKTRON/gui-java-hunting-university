package hunting.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the class for giving input on how to create the matrix 3v3 5v5 ...
 */
public class MainMenu extends JPanel {

    private final JPanel parentPanel;
    private final CardLayout cardLayout;
    JLabel textLable;
    //JTextField textField;
    JButton button;

    JRadioButton jRadioButton;
    JRadioButton jRadioButton2;
    JRadioButton jRadioButton3;


    /**
     * constructor to create the base UI for MAINMENU
     * @param parent
     * @param layout
     */
    public MainMenu(JPanel parent,CardLayout layout){


        this.parentPanel = parent;
        this.cardLayout = layout;


        this.setLayout(new GridLayout(3,3));


        textLable = new JLabel();
        textLable.setText("please select your game type");




         jRadioButton = new JRadioButton("3");
         jRadioButton2 = new JRadioButton("5");
         jRadioButton3 = new JRadioButton("7");



        Font radioFont = new Font("Arial", Font.BOLD, 24);
        jRadioButton.setFont(radioFont);
        jRadioButton2.setFont(radioFont);
        jRadioButton3.setFont(radioFont);

        Dimension radioButtonSize = new Dimension(100, 50);
        jRadioButton.setPreferredSize(radioButtonSize);
        jRadioButton2.setPreferredSize(radioButtonSize);
        jRadioButton3.setPreferredSize(radioButtonSize);

        ButtonGroup  radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(jRadioButton);radioButtonGroup.add(jRadioButton2);radioButtonGroup.add(jRadioButton3);


        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        radioPanel.add(jRadioButton);
        radioPanel.add(jRadioButton2);
        radioPanel.add(jRadioButton3);

//        textLable.setText("Type Number of NxN rows ( N >=3 and Odd )");
//        textLable.setFont(new Font("Arial",Font.BOLD,10));
//
//
//        textField = new JTextField();
//        textField.setFont(new Font("Arial",Font.BOLD,30));


        button = new JButton("START");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buttonHandler();

            }
        });



        this.add(textLable,BorderLayout.NORTH);
       // this.add(textField,BorderLayout.NORTH);
        this.add(radioPanel,BorderLayout.CENTER);
        this.add(button,BorderLayout.SOUTH);

    }


    /**
     * handle the click event and check if input is correct (is a number)
     * and check the validity of the number and send it to next panel
     */
    private void buttonHandler(){
            try {

                //int matrix = Integer.parseInt(textField.getText());
                int matrix = 0;
                if(jRadioButton.isSelected()){ matrix = Integer.parseInt(jRadioButton.getText()); }
                else if(jRadioButton2.isSelected()){ matrix = Integer.parseInt(jRadioButton2.getText()); }
                else if(jRadioButton3.isSelected()){ matrix = Integer.parseInt(jRadioButton3.getText()); }



                if( matrix < 3 || matrix % 2 == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter an odd number greater than or equal to 3.");
                }
                else {

                    GamePanel gamePanel = new GamePanel(matrix,4*matrix,parentPanel,cardLayout);

                    parentPanel.add(gamePanel,"gamePanel");

                    cardLayout.show(parentPanel,"gamePanel");


                }
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "NEEDS TO BE NUMBER");
            }


        }

}
