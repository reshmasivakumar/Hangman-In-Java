/* 
 *  This is the main HangMan program which starts the Hangman game. 
 *  The purpose of this project is to simulate a simple paper and pen word-guessing game called HangMan in which the program expects the 
 *  player of this game to guess the letters of a secret word. In this version of the gane, the HangMan program acts as one player chosing a secret 
 *  word and the user is the other player who is expected to guess the letters of the secret word within limited times(7 times). With each incorrect 
 *  guess, a part of the 'hangman' part hung to the gallows is displayed. The game ends when either the word is guessed correctly within limited 
 *  times or the 'hangman' picture display is complete. 
  *     In this version, the user can play 5 rounds. The user can choose to exit or continue at the end of each round. When the user chooses to 
 *  exit or 5 rounds are over, the program displays the statistics of the players scores in all 5 rounds.
.*                
 * 
 * This Program illustrates the following topics of the course:
 *  1) Functional Decomposition - The functionality is coded as smaller methods for better readability and modularity
 *  2) Looping - If loop, For loop, do/while are  used
 *  3) Nested Loops - Nested for loops are used to display the Score statistics as 2 dim array
 *  4) Branching with selection control - if/else and switch case are used
 *  5) File IO - The secret words to be guessed are read from a text file - hangmanSecretWords.txt 
 *              - The player statistics written to an output text file - HangManScoreBoard.txt
 *  6) Using Multiple Classes - HangmanDriver is the main program which uses HangMan Class where the game is coded
 *  7) Arrays - Single dimensional and 2-dim arrays are used
 *  8) Exceptional Handling - try/catch blocks used for catching FileNotFoundException while reading from file
 *  9) Class design using composition - attempted coding the hangman bodyparts display as inner class 
 *  10)Class Design using Access Modifiers - All instance variables are declared as private and getters and setters used
 *  11) Multi-dimensional arrays - 2 dim arrays used to store and display the score statistics
 *  12) GUI and Graphics - JOptionPane used to display dialogs
 *  
 *    * 
 * Author - Reshma Sivakumar
 * Date - 08/19/2015 
 */

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class HangManDriver  {

    public static void main(String[] args) {
        Scanner wordsFile = null;                  // words data file
        int noOfPlays = 0;
        int numberofPlaysAllowed = 5;
        // open the file containing the words
        try {
            wordsFile = new Scanner(new FileInputStream("hangmanSecretWords.txt"));
            }
        catch (FileNotFoundException e) {
            System.out.println("Error accessing File.");
            System.exit(0);
        }

        HangMan hangman = new HangMan();
        hangman.setInputFile(wordsFile);
        hangman.setNumberofPlaysAllowed(numberofPlaysAllowed);
        Scanner input = new Scanner(System.in);

        // display an introduction on the game to the player
        hangman.showGameRules();

        // play new games till user selects n/N
        String userChoice;
        do {
        noOfPlays = noOfPlays + 1 ;
        hangman.play(noOfPlays);
        System.out.println("noOfPlays "+noOfPlays);
  
        System.out.print("Do you want to play again? Enter Y to continue and N to exit ");
         
        userChoice = input.nextLine();

     } while (userChoice.toUpperCase().startsWith("Y") &&  noOfPlays < numberofPlaysAllowed);
 
        System.out.println();
        JOptionPane.showMessageDialog(null,"Thanks for playing... Here are your scores..");
         hangman.printScoreBoard();
        hangman.writeScoreBoardToFile();
         System.out.println();
        wordsFile.close(); //close inputfile
        hangman.getOutputFile().close(); //close outputfile
 
        }
    
    }

