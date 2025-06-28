package hunting.ui;

import hunting.gameBuilder.GameBuilder;
import hunting.player.Player;
import hunting.util.PlayerPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * the class for making the UI game panel with
 * rules of the game base on player click event
 *
 */
public class GamePanel extends JPanel {

    private final GameBuilder gameBuilder;
    private final JPanel parentPanel;
    private final CardLayout cardLayout;

    private JPanel header;
    private JPanel body;
    private JPanel footer;

    private ArrayList<JButton> greenSelect;
    private JButton selectedPlayer;

    private JLabel informationLabel;
    private JLabel roundNumberLabel;
    private JLabel winnerLable;

    private JPanel statusLight;

    /**
     * constructor getting this params and making the first UI base on BorderLayout
     * 
     * @param matrix
     * @param endTurn
     * @param parent
     * @param layout
     */
    public GamePanel(int matrix, int endTurn, JPanel parent, CardLayout layout) {

        this.gameBuilder = new GameBuilder(matrix, endTurn);

        this.parentPanel = parent;
        this.cardLayout = layout;

        this.greenSelect = new ArrayList<>();

        this.setLayout(new BorderLayout());

        createHeader();

        createBody(matrix);

        creatingFooter();
    }

    /**
     * method for creating UI for header of out boarderLayout
     */
    private void createHeader() {

        header = new JPanel();
        informationLabel = new JLabel();
        informationLabel.setText("game is running");

        header.add(informationLabel);

        this.add(header, BorderLayout.NORTH);

        roundNumberLabel = new JLabel();
        roundNumberLabel.setText("round : 0");

        header.add(roundNumberLabel);

        statusLight = new JPanel();
        statusLight.setBackground(Color.red);

        header.add(statusLight);

        winnerLable = new JLabel();
        winnerLable.setText("");

        header.add(winnerLable);

    }

    /**
     * base on this param (n) , creating NxN matrix button as gridLayout , in the
     * center of BorderLayout
     * which will be game area
     * 
     * @param n
     */
    private void createBody(int n) {

        body = new JPanel(new GridLayout(n, n));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                JButton button = new JButton();
                button.setBounds(new Rectangle(20, 20));

                String position = gameBuilder.getGameBoard().getPositions()[i][j].getValue();

                button.setText(position);
                button.putClientProperty("player", gameBuilder.getGameBoard().getPositions()[i][j].getPlayer());
                button.putClientProperty("position", gameBuilder.getGameBoard().getPositions()[i][j]);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameHandler(e);
                    }
                });

                body.add(button);
            }
        }

        this.add(body, BorderLayout.CENTER);

    }

    /**
     * this will be footer UI which handles information and reset button
     */
    private void creatingFooter() {

        footer = new JPanel();

        JButton reset = new JButton("reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButton();
            }
        });
        footer.add(reset);

        this.add(footer, BorderLayout.SOUTH);
    }

    /**
     * method for reseting the game which goes back in cardLayout to MainMenu
     */
    private void resetButton() {
        cardLayout.show(parentPanel, "MainMenu");
    }

    /**
     * important method which handles the event when some one click on any buttons
     * 
     * @param event
     */
    private void gameHandler(ActionEvent event) {

        Object source = event.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;

            if (selectedPlayer != null) {
                Player player = (Player) selectedPlayer.getClientProperty("player");

                if (validateMoveButton(player, button)) {
                    PlayerPosition newPosition = (PlayerPosition) button.getClientProperty("position");

                    if (gameBuilder.run(player, newPosition)) {

                        movePlayerButtonHandler(selectedPlayer, button);
                        roundNumberLabel.setText(Integer.toString(gameBuilder.getTurn()));
                        selectedPlayer = null;
                        deColoringButton();

                        String winner = gameBuilder.winner();

                        if (winner != null && gameBuilder.isGameOver) {
                            statusLight.setBackground(Color.green);
                            winnerLable.setText(winner);
                            informationLabel.setText("game is ended");
                        }

                    }
                }
            } else {

                Player player = (Player) button.getClientProperty("player");
                if (player != null && gameBuilder.varifyPlayerTurn(player.getRole())) {
                    selectedPlayer = button;
                    coloringButtonPositions((Player) selectedPlayer.getClientProperty("player"));
                }

            }

        }
    }

    /**
     * method to handle UI just for the case of showing where a player can go
     * 
     * @param player
     */
    private void coloringButtonPositions(Player player) {
        ArrayList<PlayerPosition> listPositions = gameBuilder.getGameBoard().verifiedPositions(player);

        for (Component component : body.getComponents()) {
            if (component instanceof JButton) {
                JButton newButton = (JButton) component;
                PlayerPosition buttonPosition = (PlayerPosition) newButton.getClientProperty("position");

                if (listPositions.contains(buttonPosition)) {
                    greenSelect.add(newButton);
                    newButton.setBackground(Color.GREEN);

                }
            }
        }

    }

    /**
     * method for decoloring the selected buttons after player move
     */
    private void deColoringButton() {
        for (JButton button : greenSelect) {
            button.setBackground(UIManager.getColor("Button.background"));
        }
        greenSelect.clear();
    }

    /**
     * first layer of validation to check if a player can move to certain button
     * 
     * @param player
     * @param button
     * @return
     */
    private boolean validateMoveButton(Player player, JButton button) {

        ArrayList<PlayerPosition> listPositions = gameBuilder.getGameBoard().verifiedPositions(player);

        PlayerPosition buttonPosition = (PlayerPosition) button.getClientProperty("position");
        Player buttonPlayer = (Player) button.getClientProperty("player");

        return listPositions.contains(buttonPosition) && buttonPlayer == null
                && gameBuilder.varifyPlayerTurn(player.getRole());
    }

    /**
     * Ui base handler to move the player UI from where he was to where he should go
     * 
     * @param oldButton
     * @param newButoon
     */
    private void movePlayerButtonHandler(JButton oldButton, JButton newButoon) {
        JButton tempButton = new JButton();
        tempButton.setText(oldButton.getText());
        tempButton.putClientProperty("player", (Player) oldButton.getClientProperty("player"));

        oldButton.setText(newButoon.getText());
        oldButton.putClientProperty("player", newButoon.getClientProperty("player"));

        newButoon.setText(tempButton.getText());
        newButoon.putClientProperty("player", tempButton.getClientProperty("player"));

    }

}
