package hunting.util;

import hunting.player.Player;

import java.util.Objects;


/**
 * a utility class for making a PlayerPosition to be added to buttons and players
 */
public class PlayerPosition {

        private   int i;
        private   int j;


        private Player player;

        private String value;

    public int getI() {
        return i;
    }


    public int getJ() {
        return j;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * constructor to create the Positions base on i and j of matrix
     * @param i
     * @param j
     */
    public PlayerPosition(int i, int j) {
        if(i < 0 || j < 0){
            throw new IllegalArgumentException("positions can't be negative");
        }

            this.i = i;
            this.j = j;
            this.player= null;
            this.value="-";
        }

    /**
     * overiding equals base on value of i and j
     * @param obj
     * @return boolean value to determine if its same position
     */
    @Override
        public boolean equals(Object obj){
            if(obj == null || this.getClass() != obj.getClass() ){ return  false;}
            if(obj == this){ return  true;}
            else {
                PlayerPosition playerPosition = (PlayerPosition) obj;
                return this.i == playerPosition.i && this.j == playerPosition.j;
            }

        }

    /**
     * overriding hashCode base on new equals method ( base on i and j )
     * @return
     */
    @Override
        public int hashCode(){
          return Objects.hash(this.i,this.j);
        }

}
