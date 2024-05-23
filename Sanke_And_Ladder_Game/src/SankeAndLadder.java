import java.util.*;

public class SankeAndLadder {
    public static void main(String[] args) {

        int boardsize=100;
        Dice num = new Dice(1);
        Player p1 = new Player("Arul",1);
        Player p2 = new Player("Gowtham",2);
        Queue<Player> list = new LinkedList<>();
        list.offer(p1);
        list.offer(p2);

        //Create list for snake location
        Jumper snake1 = new Jumper(10,2);
        Jumper snake2 = new Jumper(99,12);
        List<Jumper> snakes = new ArrayList<>();
        snakes.add(snake1);
        snakes.add(snake2);

        // Create list for ladder location.
        Jumper ladder1 = new Jumper(5,25);
        Jumper ladder2 = new Jumper(40,89);
        List<Jumper> ladders = new ArrayList<>();
        ladders.add(ladder1);
        ladders.add(ladder2);

        //Initiate the players position at 0.
        Map<String,Integer> details = new HashMap<>();
        details.put("Arul",0);
        details.put("Gowtham",0);

        //Pass all the above values
        GameBoard bg = new GameBoard(num,list,snakes,ladders,details,boardsize);
        bg.startGame();

    }
}
