
    import java.util.*;

public class Uno {

    static class Card {
        enum Color { RED, GREEN, BLUE, YELLOW, WILD }
        enum Value { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, REVERSE, SKIP, WILD, WILD_DRAW_FOUR }

        Color color;
        Value value;

        public Card(Color color, Value value) {
            this.color = color;
            this.value = value;
        }

        @Override
        public String toString() {
            return color + " " + value;
        }
    }

    static class Player {
        String name;
        List<Card> hand;

        public Player(String name) {
            this.name = name;
            this.hand = new ArrayList<>();
        }

        public void drawCard(List<Card> deck) {
            hand.add(deck.remove(0));
        }

        public boolean canPlay(Card topCard) {
            for (Card card : hand) {
                if (card.color == topCard.color || card.value == topCard.value || card.color == Card.Color.WILD) {
                    return true;
                }
            }
            return false;
        }

        public Card playCard(Card topCard, Scanner scanner) {
            if (canPlay(topCard)) {
                System.out.println(name + ", your hand: " + hand);
                System.out.println("Top card: " + topCard);
                System.out.print("Enter the index of the card you want to play (or 'draw' to draw a card): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("draw")) {
                    return null;
                } else {
                    try {
                        int index = Integer.parseInt(input);
                        if (index >= 0 && index < hand.size()) {
                            Card playedCard = hand.remove(index);
                            System.out.println(name + " played: " + playedCard);
                            return playedCard;
                        } else {
                            System.out.println("Invalid card index.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                }
            } else {
                System.out.println(name + ", you cannot play any card. Drawing a card.");
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter player " + i + " name: ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        List<Card> deck = createDeck();
        Collections.shuffle(deck);

        // Deal initial hands
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }

        // Start the game
        Card topCard = deck.remove(0);
        System.out.println("Top card: " + topCard);

        int currentPlayerIndex = 0;
        boolean gameRunning = true;
        while (gameRunning) {
            Player currentPlayer = players.get(currentPlayerIndex);
            Card playedCard = currentPlayer.playCard(topCard, scanner);

            if (playedCard != null) {
                topCard = playedCard;
                if (playedCard.color == Card.Color.WILD) {
                    System.out.print("Choose a color (RED, GREEN, BLUE, YELLOW): ");
                    String colorInput = scanner.nextLine();
                    try {
                        topCard.color = Card.Color.valueOf(colorInput.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid color.");
                        continue;
                    }
                }
                if (playedCard.value == Card.Value.WILD_DRAW_FOUR) {
                    System.out.print("Choose a color (RED, GREEN, BLUE, YELLOW): ");
                    String colorInput = scanner.nextLine();
                    try {
                        topCard.color = Card.Color.valueOf(colorInput.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid color.");
                        continue;
                    }
                    players.get((currentPlayerIndex + 1) % numPlayers).drawCard(deck);
                    players.get((currentPlayerIndex + 1) % numPlayers).drawCard(deck);
                    players.get((currentPlayerIndex + 1) % numPlayers).drawCard(deck);
                    players.get((currentPlayerIndex + 1) % numPlayers).drawCard(deck);
                }
                if (playedCard.value == Card.Value.DRAW_TWO) {
                    players.get((currentPlayerIndex + 1) % numPlayers).drawCard(deck);
                    players.get((currentPlayerIndex + 1) % numPlayers).drawCard(deck);
                }
                if (playedCard.value == Card.Value.REVERSE) {
                    if (numPlayers == 2) {
                        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
                    } else {
                        Collections.reverse(players);
                        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
                    }
                }
                if (playedCard.value == Card.Value.SKIP) {
                    currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
                }
                if (currentPlayer.hand.isEmpty()) {
                    System.out.println(currentPlayer.name + " wins!");
                    gameRunning = false;
                }
            } else {
                currentPlayer.drawCard(deck);
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        }
        scanner.close();
    }

    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.WILD) {
                deck.add(new Card(color, Card.Value.ZERO));
                for (int i = 1; i <= 9; i++) {
                    deck.add(new Card(color, Card.Value.valueOf("ONE" + i)));
                    deck.add(new Card(color, Card.Value.valueOf("ONE" + i)));
                }
                deck.add(new Card(color, Card.Value.DRAW_TWO));
                deck.add(new Card(color, Card.Value.DRAW_TWO));
                deck.add(new Card(color, Card.Value.REVERSE));
                deck.add(new Card(color, Card.Value.REVERSE));
                deck.add(new Card(color, Card.Value.SKIP));
                deck.add(new Card(color, Card.Value.SKIP));
            }
        }
        for (int i = 0; i < 4; i++) {
            deck.add(new Card(Card.Color.WILD, Card.Value.WILD));
            deck.add(new Card(Card.Color.WILD, Card.Value.WILD_DRAW_FOUR));
        }
        return deck;
    }
}

