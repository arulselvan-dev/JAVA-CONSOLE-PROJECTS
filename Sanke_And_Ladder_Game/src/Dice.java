public class Dice {

    private int no_Of_Dice ;

    public Dice(int no_Of_Dice)
    {
        this.no_Of_Dice=no_Of_Dice;
    }
    int rollDice()
    {
        return ((int)(Math.random()*(6*no_Of_Dice-1*no_Of_Dice)))+1;
    }

}
