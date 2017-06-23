
/**
 * The HangMan java class contains the public and private methods for the word guessing game called HangMan and will be called from the
 * HangManDriver file. 
 * 
 *  Author - Reshma Sivakumar
 * Date - 08/19/2015
 */

import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import javax.swing.JOptionPane;

public class HangMan
{
    public static final int NUMBEROFGUESSESALLOWED = 7; //number of times a player can guess
    private HangManBodyPartsInner  bodyParts = new HangManBodyPartsInner(); //Creating inner class to print body parts    
    public int numberofPlaysAllowed; //number of plays allowed for a game
    private Scanner secretWordsFile; //input file where the secret words are stored for the game
    private String currentSecretWord; //current secret word from the input file
    private char guessedLetter; //letters guesses by player    
    private int numberOfCorrectGuesses=0; //number of guesses by player
    private int numberOfWrongGuesses=0; //number of wrong guesses by player    
    private char[] lettersGuessedSoFar; //letter guessed by player
    private char[] correctWordSoFar; //correct word guessed so far by player
    private boolean isLetterFound; //if letter is found in the current word
    private int numberOfRounds;  //number of rounds a player uses in a game
    private double[][] playerScores ; // 2dim array that scores the player statistics 
    private int  sumWrongGuesses= 0;  // sum of wrong guessesfv
    private int  sumCorrectGuesses= 0; //sum of correct guesses
    private int  sumGuesses= 0;    // total  guesses (sum of wrong and sum of correct guesses)
    private PrintWriter outputFile; // Output file where the  scores the player statistics are written to
    /* 
     * set the text file that contains the secret words
     *  setInputFile(Scanner inFile)
     *  returns : void
     */  
    public void setInputFile(Scanner inFile)
    {
        this.secretWordsFile = inFile;
         
    }
    
     /* 
     * set the number of rounds allowed for a player in a game
     *  setNumberofPlaysAllowed(int numberofPlaysAllowed)
     *  returns : void
     */     
     public void setNumberofPlaysAllowed(int numberofPlaysAllowed)
     {
        this.numberofPlaysAllowed = numberofPlaysAllowed;
        this.playerScores = new double[this.numberofPlaysAllowed][4];
    }
 
     /* 
     * Return the output file which contains the player score statistics
     *  getOutputFile()
     *  returns : OutputFile  which contains the player score statistics
     */     
    public PrintWriter  getOutputFile()
    {
         return this.outputFile;
    }
    
    /* 
     *  Display the rules of the game to the user as a dialog box
     *  showGameRules() 
     *  returns : void
     */  
    public void showGameRules() 
    {
        String[] message =  new String[] {"Welcome to the HangMan Game!" ,
                                          "Guess a letter of the secret word.",
                                          "You are allowed "+NUMBEROFGUESSESALLOWED+" guesses for a word.",
                                           "Every time you guess an wrong letter a body part appears on he game." ,
                                           "If you donot guess correctly before you get hung you  will lose the game"};
        System.out.println();
        JOptionPane.showMessageDialog(null, message);
        System.out.println("Lets start the game....");
        System.out.println();
        bodyParts.showGallowsOnly();
        System.out.println();
    }
    
       
     /* 
     *  Display the Score statistics for all the rounds of the user
     *  printScoreBoard()
     *  returns : None
     *  The following statistics are displayed
     *      - Round# as Column 1 
     *      - Number of Correct Guesses in the round as Column 2
     *      - Number of Wrong Guesses in the round as Column 3
     *      - Number of Guesses (Col2 + Col3 above)  as Column 4
     *      - The Total  Number of Correct Guesses (Sum of Column 2)
     *      - The Total  Number of Wrong Guesses (Sum of Column 3)
     *      - The Total  Number of Guesses (Sum of Column 4)
     */  
    public void printScoreBoard()
    {
        System.out.println("Play#"+"\t"+"Correct Guesses"+"\t"+"Wrong Guesses"+"\t"+"Total Guesses");
           for (int i=0; i< this.numberofPlaysAllowed; i++) // For each round
           {
            for (int j=0; j< 3; j++) // For each columns
            {
              System.out.print(this.playerScores[i][j] + "\t\t");
            }
             System.out.print((this.playerScores[i][1] + this.playerScores[i][2]) + "\t\t"); //print total guess
            System.out.println();
        }
        System.out.println("Total Number of Wrong Guesses "+  this.sumWrongGuesses );
        System.out.println("Total Number of Correct Guesses "+  this.sumCorrectGuesses );
        System.out.println("Total Number of  Guesses "+  this.sumGuesses );      
 
    }
   
     /* 
     *  Prints to file "HangManScoreBoard.txt" the Score statistics for all the rounds of the user
     *  writeScoreBoardToFile()
     *  returns : None
     *  The following statistics are written to file
     *      - Round# as Column 1 
     *      - Number of Correct Guesses in the round as Column 2
     *      - Number of Wrong Guesses in the round as Column 3
     *      - Number of Guesses (Col2 + Col3 above)  as Column 4
     *      - The Total  Number of Correct Guesses (Sum of Column 2)
     *      - The Total  Number of Wrong Guesses (Sum of Column 3)
     *      - The Total  Number of Guesses (Sum of Column 4)
     */     
    
    public void writeScoreBoardToFile()
    {
         try
         {
            
             outputFile = new PrintWriter(new File("HangManScoreBoard.txt"));
             for (int i=0; i< this.numberofPlaysAllowed; i++) // For each round
           {
            for (int j=0; j< 3; j++) // For each columns
            {
               outputFile.print(this.playerScores[i][j] + "\t\t");
            }
              outputFile.print((this.playerScores[i][1] + this.playerScores[i][2]) + "\t\t"); //print total guess
             outputFile.println();
          }
          outputFile.println("Total Number of Wrong Guesses "+  this.sumWrongGuesses );
          outputFile.println("Total Number of Correct Guesses "+  this.sumCorrectGuesses );
          outputFile.println("Total Number of  Guesses "+  this.sumGuesses );      
           
             //outputFile.close();
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            } 
    }
    /* 
     *  Check if user guessed a letter that exists in the current secret word from file.
     *  checkCorrectLetterGuessed(String currentWord, char letterGuessed)
     *  returns : True if letter exists, false otherwise
     */  
    private boolean checkCorrectLetterGuessed(String currentWord, char letterGuessed)
    {
        char[] tempChar = currentWord.toCharArray(); // Convert current word to char array to allow search

        for ( int i = 0; i < tempChar.length; i++) { // traverse through the char array

            if ((tempChar[i] == letterGuessed) && ( this.correctWordSoFar[i] != letterGuessed)) //check if letter guessed is part of the current word and also not previously guessed
            {

                this.correctWordSoFar[i]=letterGuessed; //store the letter guessed in the correct words guessed so far array
                return true;

            }

        }
        return false; 
    }

    /* 
     *  Prints the letters guessed so far
     *  printLettersGuessedSoFar()
     *  returns : void
     */
    private void printLettersGuessedSoFar()
    {
        System.out.print("Letters guessed already =>");
        for (int i =0; i < NUMBEROFGUESSESALLOWED; i++) 
        {
            if (this.lettersGuessedSoFar[i]!=(' '))  //check to see if the correct letter is guessed in the position of the word
                System.out.print(this.lettersGuessedSoFar[i] + " "); //print the letter 
        }
        System.out.println();
    }

    /* 
     *  Print all the letters in word guessed so far
     *  printWordSoFar()
     *  returns : void
     */    
    private void printWordSoFar()
    {
        System.out.print("The word so far => ");
        for (int j =0; j < this.currentSecretWord.length(); j++)
        {
            System.out.print(this.correctWordSoFar[j] );
        } 
        System.out.println();
    }



    /* 
     *  Play the game for the number of limited rounds
     *  play(int playCount ) 
     *  returns : void
     *  Method performs the following actions:
     *      - reads the secret word from file
     *      - initializes the numberOfWrongGuesses, numberOfCorrectGuesses, lettersGuessedSoFar, correctWordSoFar
     *      - prints the empty gallows
     *      - Prompts the user to guess the letter for first word, shows the length of the word as hint
     *      - When user enters letter, checks to see if its wrong or correct word. If wrong letter guessed, numberOfWrongGuesses incremented,
     *          lettersGuessedSoFar array  populated. If correct letter guessed, numberOfCorrectGuesses incremented, lettersGuessedSoFar array populated
     *      - For each wrong letter, bodyParts method of the Inner class HangManBodyPartsInner is called to display the Hangman parts
     *      - When the NumberOfguesses is exhausted and wrong word guessed, the entire hangman is displayed
     *      - Displays appropriate messages on Win and Failure
     *      - Finally prints the Score statistics 
     *          
     */    
    public void play(int playCount ) 

    {
        this.numberOfRounds  = playCount; // number of rounds for a game
        //initialize scanner to read from console
        Scanner keyboard = new Scanner(System.in);
        while (this.secretWordsFile.hasNextLine()) //repeat for all words in file
        {
            this.numberOfWrongGuesses = 0;
             //get one secret word from the file
            this.currentSecretWord = this.secretWordsFile.nextLine();
            this.numberOfCorrectGuesses = 0 ;
            this.lettersGuessedSoFar = new char[NUMBEROFGUESSESALLOWED];
            this.correctWordSoFar = new char[this.currentSecretWord.length()];
            //intialize currentSecretWord to all _
            for (int j=0; j<this.currentSecretWord.length(); j++)
            {
                this.correctWordSoFar[j] = '_';
            }
            
            //intialize lettersGuessedSoFar to all blanks
            for (int j = 0; j < NUMBEROFGUESSESALLOWED; j++)
            {

                this.lettersGuessedSoFar[j] = ' ';

            }
            
            for (int i=0; i< NUMBEROFGUESSESALLOWED; i++) { // loop till total guesses allowed

                System.out.print("Guess a letter for a "+ this.currentSecretWord.length()+" letter word =>");
                String line = keyboard.next();
               
                this.guessedLetter = line.charAt(0); //get a letter
                this.lettersGuessedSoFar[i] = this.guessedLetter; //store the guessed letter in lettersGuessedSoFar
                //check if letter guessed exists in current word
                this.isLetterFound = checkCorrectLetterGuessed(this.currentSecretWord, this.guessedLetter);
                if (this.isLetterFound == false)
                {
                    this.numberOfWrongGuesses ++;  //increment number of wrong guesses
                    bodyParts.setNumberOfGuesses(this.numberOfWrongGuesses);
                    bodyParts.printHangMan(); //display next hang man part
                }
                else
                {
                         this.numberOfCorrectGuesses++; //increment the number of wrong guesses
                }
                System.out.println();
                printLettersGuessedSoFar(); //Print the letters guessed so far including wrong and correct letters
                System.out.println("Number of wrong guesses =>" + this.numberOfWrongGuesses);
                printWordSoFar(); // Print the correct letters guessed so far places the letters in right position
                String currentWordsString =new String(this.correctWordSoFar); // create a string of the char array to able to compare
                //convert char array to String to compare with String 
                if (new String(this.correctWordSoFar).equals(this.currentSecretWord))
                {
                    //break out if the correct word is  guessed
                    break;
                }

            }
            //print message if correct word guessed
            if (new String(this.correctWordSoFar).equals(this.currentSecretWord))
            {
                System.out.println("Congratulations, you win!!!");
               
                break;

            }
            else//print message if wrong word guessed
            {
                System.out.println("Sorry you lose! Better luck next time!");
                System.out.println("The word was ==> "+this.currentSecretWord);
                break;
            }
        }
                //accumulate all scores into a 2-dim array
               this.playerScores[this.numberOfRounds - 1][0] = this.numberOfRounds; //Play#
               this.playerScores[this.numberOfRounds - 1][1] = this.numberOfCorrectGuesses; //correct  guesses
               this.playerScores[this.numberOfRounds - 1][2] = this.numberOfWrongGuesses; //wrong  guesses
               this.playerScores[this.numberOfRounds - 1][3] = this.numberOfCorrectGuesses + this.numberOfWrongGuesses; //total  guesses
               //aggregate the columns
               this.sumWrongGuesses +=  this.numberOfWrongGuesses;
               this.sumCorrectGuesses +=  this.numberOfCorrectGuesses;
               this.sumGuesses +=  (this.numberOfWrongGuesses + this.numberOfCorrectGuesses);
       
    }
    
    
    //inner class to display the Hangman body parts depending on the NumberOFWrongGuess count
   class HangManBodyPartsInner{
  
     private int numberOfWrongGuesses;
     
    private void setNumberOfGuesses(int numberOfWrongGuesses)
    {
            this.numberOfWrongGuesses = numberOfWrongGuesses;
    }
    private void printUpperGallows()
    {
        System.out.println("_____");
        System.out.println("|   |");
    }
    
    private void printUpperBody()
    {
        printUpperGallows();
        System.out.println("|   o");
        System.out.println("|  /|\\ "); 
      
    }
    //Show starting empty Gallows
    private void showGallowsOnly()
    {
        printUpperGallows();
        System.out.println("|   ");
        System.out.println("|   "); 
        System.out.println("|   "); 
    }

    private void drawOval()
    {
        printUpperGallows();    
        System.out.println("|   o");
        System.out.println("|    "); 
        System.out.println("|    "); 
    }

    private void drawStick()
    {
        printUpperGallows();
        System.out.println("|   o");
        System.out.println("|   | "); 
        System.out.println("|    "); 
    }

    private void drawLeftArmStick()
    {
        printUpperGallows();
        System.out.println("|   o");
        System.out.println("|  /| ");
        System.out.println("|    "); 
    }

    private void drawRightArmStick()
    {
        printUpperBody();    
        System.out.println("|       ");
    }

    private void drawRightLegStick()
    {
        printUpperBody();    
        System.out.println("|    \\"); 
    }

    //Final wrong guess -hung man
    private void drawComplete()
    {
        printUpperBody(); 
        System.out.println("|  / \\"); 
    }
  
    //method to display hangman

 
    private void printHangMan()
    {
        switch (this.numberOfWrongGuesses)
        {
            case 1 : drawOval();
            break;
            case 2: drawStick();
            break;

            case 3: drawLeftArmStick();
            break;

            case 4: drawRightArmStick();
            break;

            case 5: drawRightLegStick();
            break;

            default: drawComplete();
            break;

        }

    }
}
   
}

