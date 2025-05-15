//Colton Dean
//This is my uno project for 4.3
//This is for my software and programing class

import java.util.*;

class Card {
    String color;
    String value;

    Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}

class Deck {
    private List<Card> cards;

    Deck() {
        cards = new ArrayList<>();
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw Two"};

        for (String color : colors) {
            for (String value : values) {
                cards.add(new Card(color, value));
                if (!value.equals("0")) { // Add another copy of 1-9 cards
                    cards.add(new Card(color, value));
                }
            }
        }
        // Add Wild cards
        cards.add(new Card("Wild", "Wild"));
        cards.add(new Card("Wild", "Wild"));
        cards.add(new Card("Wild", "Draw Four"));
        cards.add(new Card("Wild", "Draw Four"));
    }

    public Card drawCard() {
        return cards.remove(new Random().nextInt(cards.size()));
    }
}

class Player {
    String name;
    List<Card> hand;

    Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    void playCard(Card card) {
        hand.remove(card);
    }

    @Override
    public String toString() {
        return name + "'s hand: " + hand;
    }
}
public class UnoGame {
    private Deck deck;
    private List<Player> players;
    private Card topCard;
    private int currentPlayerIndex = 0;

    public UnoGame(List<String> playerNames) {
        deck = new Deck();
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        // Draw initial cards
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }
        // Draw the first top card
        topCard = deck.drawCard();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameActive = true;

        while (gameActive) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer);
            System.out.println("Top card: " + topCard);

            System.out.println("Choose a card to play or type 'draw' to draw a card:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("draw")) {
                currentPlayer.drawCard(deck);
            } else {
                Card cardToPlay = null;
                for (Card card : currentPlayer.hand) {
                    if (card.toString().equalsIgnoreCase(input)) {
                        cardToPlay = card;
                        break;
                    }
                }
                if (cardToPlay != null && (cardToPlay.color.equals(topCard.color) || cardToPlay.value.equals(topCard.value) || cardToPlay.value.equals("Wild"))) {
                    currentPlayer.playCard(cardToPlay);
                    topCard = cardToPlay;
                    System.out.println(currentPlayer.name + " played: " + cardToPlay);
                } else {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }
            }

            // Check for win condition
            if (currentPlayer.hand.isEmpty()) {
                System.out.println(currentPlayer.name + " wins!");
                gameActive = false;
            }

            // Move to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        List<String> playerNames = Arrays.asList("Alice", "Bob");
        UnoGame game = new UnoGame(playerNames);
        game.play();
    }
}

