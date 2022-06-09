public class CrapsDriver {

    public static void main(String[] args) {
        Craps blackjack = new Craps();
        do{
            blackjack.display();

        }while(Craps.move(Craps.playAgain()));
        
    }
}