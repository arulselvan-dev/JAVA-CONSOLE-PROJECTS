import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GameBoard {

    private Dice dice;
    private Queue<Player> nextTurn;
    private List<Jumper> snakes;
    private List<Jumper> ladder;
    private Map<String,Integer> currentPosition;
    int boardSize;
    public GameBoard(Dice dice,Queue<Player> nextTurn,List<Jumper> snakes,List<Jumper> ladder,Map<String,Integer> currentPosition,int boardSize)
    {
        this.dice=dice;
        this.nextTurn=nextTurn;
        this.snakes=snakes;
        this.ladder=ladder;
        this.currentPosition=currentPosition;
        this.boardSize=boardSize;
    }
    void startGame()
    {
        while(nextTurn.size()>1)
        {
            Player player = nextTurn.poll();
            int currPos = currentPosition.get(player.getName());
            int diceValue = dice.rollDice();
            int nextCell = currPos+diceValue;
            //User rolled the dice

            //Now move the coin
            if(nextCell>boardSize)
            {
                nextTurn.offer(player);
            }
            else if (nextCell==boardSize)
            {
                System.out.println(player.getName()+" won the game");
            }
            else
            {
                int[] nextPosition = new int[1];
                boolean[] val = new boolean[1];
                nextPosition[0]=nextCell;
                snakes.forEach(v->
                {
                    if(v.startPoint==nextCell)
                    {
                        nextPosition[0] = v.endPoint;
                    }
                });
                if(nextPosition[0]!=nextCell)
                {
                    System.out.println(player.getName()+" Bitten by snake");
                }
                ladder.forEach(v->
                {
                    if(v.startPoint==nextCell)
                    {
                        nextPosition[0]=v.endPoint;
                        val[0] =true;
                    }
                });
                if(nextPosition[0]!=nextCell && val[0])
                {
                    System.out.println(player.getName()+" GotLadder");
                }
                if(nextPosition[0]==boardSize)
                {
                    System.out.println(player.getName()+" won the game");
                }else
                {
                    currentPosition.put(player.getName(),nextPosition[0]);
                    System.out.println(player.getName()+" is at position "+nextPosition[0]);
                    nextTurn.offer(player);
                }
            }
        }
    }

}
