import java.util.Scanner;

public class Craps {
     static Scanner scan = new Scanner(System.in);
     private static int playerWins = 0;
     private static int dealerWins = 0;

     public Craps() {
     }

     public void display() {

          Player player = new Player(), dealer = new Player();
          Deck pokers = new Deck();
          char anotherCard;

          player.push(pokers.draw());
          player.push(pokers.draw());

          // 成績計算

          System.out.println("\n>>> Player has won " + playerWins + "<<<");
          System.out.println(">>> Dealer has won " + dealerWins + "<<<\n");
          System.out.println("Player cards: ");
          player.showCards();

          // 1跟10的話就贏
          if (player.checkBlackjack()) {
               System.out.println("\nBlackjack, You win!!!!!");
               playerWins = playerWins + 1;

          }
          // 非1跟10
          else {
               while (true) {
                    // 是否要牌
                    System.out.println("\nCurrent: " + player.getCardSum() + "\n");
                    // 要抽牌
                    anotherCard = getAnotherCard();
                    if (move(anotherCard)) {
                         Card newCard = pokers.draw();
                         player.push(newCard);
                         System.out.print("New card = ");
                         System.out.println("Suit:" + newCard.getSuit() + ", Rank:" + newCard.getRank() + ", Value:"
                                   + newCard.getValue());
                         System.out.println("\nPlayer's total is: " + player.getCardSum() + "\n");
                         // 爆牌莊贏
                         if (player.getCardSum() > 21) {
                              System.out.println("\n *** Bust ***");
                              dealerWins = dealerWins + 1;
                              break;
                         }
                         // 剛好21點玩家贏
                         if (player.getCardSum() == 21) {
                              System.out.println("\n!!! Blackjack !!! You win!");
                              playerWins = playerWins + 1;
                              break;
                         }
                    }
                    // 不抽牌，莊抽
                    else {
                         if (dealer.getCardSum() < player.getCardSum()) {
                              do {
                                   Card newCard = pokers.draw();
                                   dealer.push(newCard);
                                   System.out.print("New card = ");
                                   System.out.println("Suit:" + newCard.getSuit() + ", Rank:" + newCard.getRank()
                                             + ", Value:" + newCard.getValue());
                                   System.out.println("\nThe dealer's total is: " + dealer.getCardSum());

                                   if (dealer.getCardSum() == 21 || dealer.getCardSum() > player.getCardSum())
                                        break;
                              } while (dealer.getCardSum() <= 21);

                              System.out.println("\nFinal result\n");
                              System.out.println("\nYour total is: " + player.getCardSum());
                              System.out.println("\nDealer's total is: " + dealer.getCardSum());

                              if (dealer.getCardSum() > player.getCardSum() && dealer.getCardSum() <= 21) {
                                   System.out.println("You lose QAQ");
                                   dealerWins = dealerWins + 1;
                              } else if (dealer.getCardSum() > 21 || dealer.getCardSum() < player.getCardSum()) {
                                   System.out.println("You win!!! ^o^");
                                   playerWins = playerWins + 1;
                              } else if (dealer.getCardSum() == player.getCardSum()) {
                                   System.out.println("Push! -_-");
                                   dealerWins = dealerWins + 1;
                                   playerWins = playerWins + 1;
                              }
                         }
                         System.out.println();
                         break;
                    }
               }
          }
          pokers.shuffle();
          player.clearDeck();
          dealer.clearDeck();

     }

     public static char playAgain() {
          System.out.print("\nDo you want to play again? (y/n): ");
          char next_move = scan.next().charAt(0);
          if (!move(next_move)) {
               System.out.println("\n>>> Player won: " + playerWins + " <<<");
               System.out.println(">>> Dealer won: " + dealerWins + " <<<\n");

               if (playerWins > dealerWins)
                    System.out.println("Congratualations! You are winner!!!!!!");
               else if (playerWins < dealerWins)
                    System.out.println("Sorry, the dealer beats you...");
               else
                    System.out.println("Even break. No winners no losers.");
          }
          return next_move;
     }

     public static char getAnotherCard() {
          System.out.print("Would you like another card? (y/n): ");
          char a_card = scan.next().charAt(0);

          return a_card;
     }

     public static boolean move(char x) {
          if (x == 'y') {
               System.out.print("\nAnswer: YES\n");
               return true;
          } else {
               System.out.print("\nAnswer: NO\n");
               return false;
          }
     }
}
