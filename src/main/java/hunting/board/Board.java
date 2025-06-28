package hunting.board;


import hunting.player.Player;
import hunting.util.Role;
import hunting.util.PlayerPosition;

import java.util.ArrayList;



/**
 * class for creating the board where players positions are
 * and where the matrix ( the data ) will be saved
 */
public class Board {
   private PlayerPosition[][] positions;

    public PlayerPosition[][] getPositions() {
        return positions;
    }

    private Player fugitive;
    private ArrayList<Player> hunters;

    public Player getFugitive() {
        return fugitive;
    }

    /**
     * constructor to create the board base on the param n
     * creating NxN positions , making figutive and hunters and giving them their base positions
     * and throw the exception if the n is not the right argument
     * @param n
     */
    public Board(int n){

        if( n <= 2 || n % 2  == 0) {
            throw new IllegalArgumentException("Board needs to be odd number and more than 2");
        }

        positions = new PlayerPosition[n][n];
        hunters = new ArrayList<>();


        int middle = (n-1)/2;

        for (int i = 0 ; i < n ; i++ ) {
            for (int j = 0 ; j < n ; j++) {
                positions[i][j] = new PlayerPosition(i,j);

                if(i == middle && j == middle ){
                    fugitive = new Player(positions[i][j], Role.FUGITIVE,this);
                    positions[i][j].setValue("F");
                    positions[i][j].setPlayer(fugitive);
                } else if (
                        ( i == 0 && j == 0 ) ||
                        (i == 0 && j == n-1 ) ||
                        (i == n-1 && j == 0 ) ||
                        (i == n-1 && j == n-1 ) ) {
                    Player hunter = new Player(positions[i][j],Role.HUNTER,this);
                    hunters.add(hunter);
                    positions[i][j].setValue("H");
                    positions[i][j].setPlayer(hunter);
                }
            }
        }
    }


    /**
     * base method for getting the varified position which player can move cross the board
     * @param player
     * @return
     */
    public ArrayList<PlayerPosition> verifiedPositions(Player player){

        if (player == null || player.getPlayerPosition() == null) {
            throw new IllegalArgumentException("Board.verifiedPositions can not varify null player or player's position");
        }

        ArrayList<PlayerPosition> verified = new ArrayList<>();

        int currentI = player.getPlayerPosition().getI();
        int currentJ = player.getPlayerPosition().getJ();

        PlayerPosition[][] matrix = this.positions;
        for ( int i = Math.max(0,currentI - 1)  ; i <= Math.min(matrix.length - 1, currentI + 1); i++){
            for ( int j = Math.max(0,currentJ - 1)  ; j <= Math.min(matrix[0].length - 1, currentJ + 1); j++) {

                if (i == currentI && j == currentJ || i != currentI && j != currentJ) {
                    continue;
                }
                    if(!matrix[i][j].getValue().equals("H") && !matrix[i][j].getValue().equals("F")){
                        verified.add(matrix[i][j]);
                    }
            }
        }
      return verified;
    }






}
