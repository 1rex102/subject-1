// Poker Game Program (AKA Analog Poker)
// By: Henry Smith
// Started: May 6, 2025
// Finished: May 12, 2025


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.stream.Collectors;
import java.io.*;

public class mainGame {

// 1. The starting point of the code
    public static void main(String[] args) {
    
        int handSize = 7;
        int cardSize = 15;
        int playerCardSum = 0;
        int handsLeft = 3;
        int pSel1 = 0, pSel2 = 0, pSel3 = 0, pSel4 = 0, pSel5 = 0;
        String playerInput;
        int currentScore = 0;
        int totalScore = 0;
        int equalValAmn;
        int roundAmount = 3;
        int handStartIndex = 0;
        int handEndIndex = 7;

// 2. Creates a copy of the deck from "deckClass.java" and shuffles it
        System.out.println(" "); 
        System.out.println("Downloading Deck from [deckClass]...");  
        deckClass TheDeck = new deckClass();
        System.out.println("Preparing Deck..."); 
        List<cardClass> Deck = TheDeck.fillTheDeck();
        List<cardClass> NewDeck = new ArrayList<>();
        NewDeck.addAll(Deck);
        Collections.shuffle(NewDeck);

// 3. Creates the "List<String> uiLines" variable the is used later to produce the make-shift interface inside "gameUI.txt", and starts up the input scanner
        System.out.println("Preparing Extra Assets..."); 
        String uiFilePath = "gameUI.txt";
        List<String> uiLines = new ArrayList <>();
        Scanner UserI = new Scanner(System.in);
        System.out.println("Program is Ready!"); 
        System.out.println(" "); 
        System.out.println("NOTE: PLEASE REVIEW THE FILE [gameInstructions.txt] LOCATED IN THIS PROGRAM'S FOLDER TO LEARN HOW TO PLAY!"); 
        System.out.println(" "); 
        System.out.println(" "); 

// 4. Starts the "for()" loop that repeats for each round, and creates the PlayerHand sub list

        for (int r = 1; r <= roundAmount; r++) {
            uiLines.clear();
            for (int x = 0; x <= 4; x++) {
                System.out.print("------------------------------");
            }
            System.out.println(" "); 
            System.out.println("ROUND "+r+" START!"); 
            if (r >= 2) {
                handStartIndex += 7;
                handEndIndex += 7;
                handsLeft -= 1;
            }
            List<cardClass> cPlayerHand = new ArrayList<>();{
                cPlayerHand.addAll(NewDeck.subList(handStartIndex ,handEndIndex));
            }

// 5. Creates the print-outs for the cards in the player's hand
            try (BufferedWriter writerA = new BufferedWriter(new FileWriter(uiFilePath))) {
                for (cardClass Card : cPlayerHand) {
                    writerA.write(Card.toString()); 
                    writerA.write(System.lineSeparator());
                }   
            } catch (IOException e) {
                e.printStackTrace();
            }

// 6. Re-reads the "gameUI.txt" file to reorder the lines of the cards to align vertically
            try (BufferedReader readerA = new BufferedReader(new FileReader(uiFilePath))) {
                String uiLine;
            while ((uiLine = readerA.readLine()) != null) {
                uiLines.add(uiLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

// 7. Re-creates the full UI for the game in the "gameUI.txt" file
            try (BufferedWriter writerB = new BufferedWriter(new FileWriter(uiFilePath))) {
                writerB.write("[][][]  []      [][][]  []  []  [][][]  [][][]    []  []  [][][]  [][]    [][]  ");
                writerB.newLine();
                writerB.write("[]  []  []      []  []  []  []  []      []        []  []  []  []  []  []  []  []");
                writerB.newLine();
                writerB.write("[][][]  []      [][][]  [][][]  [][][]  []        [][][]  [][][]  []  []  []  []");
                writerB.newLine();
                writerB.write("[]      []      []  []    []    []      []        []  []  []  []  []  []  []  []");
                writerB.newLine();
                writerB.write("[]      [][][]  []  []    []    [][][]  []        []  []  []  []  []  []  [][]  ");
                writerB.newLine();
                writerB.write(" ");
                writerB.newLine();
                for (int x = 0; x <= 4; x++) {
                    writerB.write("------------------------------");
                }
                    writerB.newLine();
                    writerB.write(" ");
                    writerB.newLine();
                for (int i = 0; i < cardSize; i++) {
                    for (int j = 0; j < handSize; j++) {
                        int index = i + j * cardSize;
                        if (index < uiLines.size()) {
                            writerB.write(String.format("%-15s", uiLines.get(index)));
                        } else {
                            writerB.write(String.format("%-15s", ""));
                        }
                    }
                    writerB.write(System.lineSeparator());
                }   
                writerB.write("Hands Left To Play: [" + handsLeft + "]");
                writerB.newLine();
                writerB.write("Total Score: [" + currentScore + "]");
                writerB.newLine();
                for (int x = 0; x <= 4; x++) {
                    writerB.write("------------------------------");
                }
            } catch (IOException e) {
                System.out.println("An error occured ");
                e.printStackTrace();
            } 

// 8. Asks the user to input their selected cards for the hand they will play    
            System.out.println(" ");
            System.out.println("Please Input Hand, with '/' between each card number [1-7] ( Ex. '1/2/4/6/7' or '1/2/3/4/0' <- First Card is always at index 0! )");
            playerInput = UserI.nextLine();
            String[] playerInputINT = playerInput.split("/");
            try {
                pSel1 += Integer.parseInt(playerInputINT[0]);
                pSel2 += Integer.parseInt(playerInputINT[1]);
                pSel3 += Integer.parseInt(playerInputINT[2]);
                pSel4 += Integer.parseInt(playerInputINT[3]);
                pSel5 += Integer.parseInt(playerInputINT[4]);
            } catch (NumberFormatException e) {
                System.out.println("An error occured ");
                e.printStackTrace();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("An error occured ");
                e.printStackTrace();

            }

// 9. Checks the player's input and uses it to add the selected cards to the "playedHand" sublist
            List<cardClass> playedHand = new ArrayList<>();{
                if (pSel1 > 7) {
                    System.out.println("Card Selection OUT OF BOUNDS");
                } else {
                    cardClass cardToAdd1 = cPlayerHand.get(pSel1);
                    playedHand.add(cardToAdd1);
                    playerCardSum += cardToAdd1.getValue();
                }
                if (pSel2 > 7) {
                    System.out.println("Card Selection OUT OF BOUNDS");
                } else {
                    cardClass cardToAdd2 = cPlayerHand.get(pSel2);
                    playedHand.add(cardToAdd2);
                    playerCardSum += cardToAdd2.getValue();
                }
                if (pSel3 > 7) {
                    System.out.println("Card Selection OUT OF BOUNDS");
                } else {
                    cardClass cardToAdd3 = cPlayerHand.get(pSel3);
                    playedHand.add(cardToAdd3);
                    playerCardSum += cardToAdd3.getValue();
                }
                if (pSel4 > 7) {
                    System.out.println("Card Selection OUT OF BOUNDS");
                } else {
                    cardClass cardToAdd4 = cPlayerHand.get(pSel4);
                    playedHand.add(cardToAdd4);
                    playerCardSum += cardToAdd4.getValue();
                }
                if (pSel5 > 7) {
                    System.out.println("Card Selection OUT OF BOUNDS");
                } else {
                    cardClass cardToAdd5 = cPlayerHand.get(pSel5);
                    playedHand.add(cardToAdd5);
                    playerCardSum += cardToAdd5.getValue();
                }
            }
            pSel1 -= pSel1;
            pSel2 -= pSel2;
            pSel3 -= pSel3;
            pSel4 -= pSel4;
            pSel5 -= pSel5;

// 10. Checks to see which poker hands the user may have played
            System.out.println(" ");
            System.out.println("Now calculating hand value...");
            List<String> allSuits = playedHand.stream()
                .map(cardClass::getSuit)
                .collect(Collectors.toList());
            List<Integer> allValues = playedHand.stream()
                .map(cardClass::getValue)
                .collect(Collectors.toList());
            String allValueOld = allValues.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            String allSuitsOld = allSuits.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            String allHandValues = String.join("", "[", allValueOld, "]");
            String allHandSuits = String.join("", "[", allSuitsOld, "]");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Suits for hand played = "+ allHandSuits);
            System.out.println(" ");
            System.out.println("Values for hand played = "+ allHandValues);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Score = "+ playerCardSum);

// 11. Calculates the total score for the played hand
            totalScore += playerCardSum;
            if (scoreManager.checkFlush(allHandSuits) == true) {
                totalScore *= 5;
                System.out.println("+ FLUSH (x5 Mult)");
            }
            if (scoreManager.checkStraight(allHandValues) == true) {
                totalScore *= 6;
                System.out.println("+ STRAIGHT (x6 Mult)");
            }
            equalValAmn = scoreManager.checkOther(allHandValues);
            if (scoreManager.checkPair(equalValAmn) == true) {
                totalScore *= 2;
                System.out.println("+ PAIR (x2 Mult)");
            }
            if (scoreManager.checkTwoPair(equalValAmn) == true) {
                totalScore *= 3.5;
                System.out.println("+ TWO PAIR (x3.5 Mult)");
            }
            if (scoreManager.checkFullHouse(equalValAmn) == true) {
                totalScore *= 6;
                System.out.println("+ FULL HOUSE (x5 Mult)");
            }
            currentScore += totalScore;
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Hand 1 Score = [ " + totalScore+" ]!");
            System.out.println(" ");

// 12. Resets the UI
            try (BufferedWriter writerC = new BufferedWriter(new FileWriter(uiFilePath))) {
                writerC.write("RESETTING..."); 
                writerC.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }   

// 13. End of the program
    try (BufferedWriter writerD = new BufferedWriter(new FileWriter(uiFilePath))) {
        writerD.write("THE PROGRAM HAS FINISHED"); 
        writerD.newLine();

    } catch (IOException e) {
        e.printStackTrace();
    }
 
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("GAME FINISHED");
        UserI.close();
    }
} {
    
}
