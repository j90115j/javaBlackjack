public class Player extends Deck {
    public Player() {
    }

    public void shuffle() {
    }

    public void showCards() {
        for (int i = 0; i < this.getCards().size(); i++) {
            System.out.println("Suit:" + this.getCards().get(i).getSuit() + " Rank:"
                    + this.getCards().get(i).getRank() + " Value:" + this.getCards().get(i).getValue());
        }
    }
    public boolean checkBlackjack(){
        if(this.getCards().size()>=2){
            int card1 = this.getCards().get(0).getValue(), card2 = this.getCards().get(1).getValue();
            if(card1 == 1 && card2 == 10 || card1 == 10 && card2 == 1){
                // System.out.println("blackjack!");
                return true;
            }
            // System.out.println("No blackjack!");
            return false;
        }
        else{
            System.out.println("You must have at least 2 cards!");
            return false;
        }
    }

}