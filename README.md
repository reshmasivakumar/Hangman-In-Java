# Hangman-In-Java
Welcome to the HangMan Game!

_____
|   |
|   o
|  /|\ 
|  / \

VERSION : 1
DATE: 8/19/2015
AUTHOR: Reshma Sivakumar

PURPOSE:
	The purpose of this project is to simulate a simple paper and pen word-guessing game called HangMan in which the program expects the player of this game to guess the letters of a secret word. In this version of the gane, the HangMan program acts as one player chosing a secret word and the user is the other player who is expected to guess the letters of the secret word within limited times(7 times). With each incorrect guess, a part of the 'hangman' part hung to the gallows is displayed. The game ends when either the word is guessed correctly within limited times or the 'hangman' picture display is complete. 
	In this version, the user can play 5 rounds. The user can choose to exit or continue at the end of each round. When the user chooses to exit or 5 rounds are over, the program displays the statistics of the players scores in all 5 rounds.

PRE-REQUISITES:
	
 	The project assumes the user has BlueJ environment installed and running. If not please download it from  http://www.bluej.org/
	
HOW TO RUN THE PROGRAM:

	If you have installed BlueJ, you can open the BlueJ Project file and run the HangManDriver class.

HOW TO PLAY THE GAME:

The player is allowed 5 rounds to play. At the end of each round the player can choose to exit the round. The program prompts the user to guess one letter at a time from the secret word. The word will be displayed as all '-'. User can guess the letter in any order. When the user selects a correct letter, the letter is placed in the correct position of the word and that letter is shown. The letters that are guessed already are also displayed. At the each wrong guess each bodypart of the Hangman will be shown. When 7 wrong guesses are made, the entire Hangman picture is displayed and the secret word will be shown to the user. If the user guesses all correct within 7 attempts, the player is declared as winner of that round. In either case of correct guess or wrong guess, when a round gets over, the player can chose to continue or quit the game. In total, a player can play 7 rounds in a game. At the end of the game the player score statistics will be shown. 

Sample Game: 

	The game starts with a welcome window and the player can enter OK. The screen displays empty gallows and prompts the user to enter the <n> letter secret word. 

	Scenario 1: User guesses Wrong Word.

---------------------------------------------------------- 

Lets start the game....

_____
|   |
|   
|   
|   

Guess a letter for a 4 letter word =>k
_____
|   |
|   o
|    
|    

Letters guessed already =>k 
Number of wrong guesses =>1
The word so far => ____
Guess a letter for a 4 letter word =>s
_____
|   |
|   o
|   | 
|    
Letters guessed already =>k s 
Number of wrong guesses =>2
The word so far => ____
Guess a letter for a 4 letter word =>a

 _____
|   |
|   o
|  /| 
|    

Letters guessed already =>k s g 
Number of wrong guesses =>3
The word so far => ____
Guess a letter for a 4 letter word =>a

Letters guessed already =>k s g a 
Number of wrong guesses =>3
The word so far => _a__
Guess a letter for a 4 letter word =>k
_____
|   |
|   o
|  /|\ 
|       

Letters guessed already =>k s g a k 
Number of wrong guesses =>4
The word so far => _a__
Guess a letter for a 4 letter word =>s
_____
|   |
|   o
|  /|\ 
|    \

Letters guessed already =>k s g a k s 
Number of wrong guesses =>5
The word so far => _a__
Guess a letter for a 4 letter word =>f
_____
|   |
|   o
|  /|\ 
|  / \

Letters guessed already =>k s g a k s f 
Number of wrong guesses =>6
The word so far => _a__
Sorry you lose! Better luck next time!
The word was ==> java
noOfPlays 1
Do you want to play again? Enter Y to continue and N to exit
-------------------------------------------------------------------

If User selects Y - round 2 begins as shown. Lets say user guesses next correct word. No hangman is displayed and he wins the round


SCENARIO : User guesses correct
--------------------------------------------------------------------
	

Letters guessed already =>f 
Number of wrong guesses =>0
The word so far => f__
Guess a letter for a 3 letter word =>u

Letters guessed already =>f u 
Number of wrong guesses =>0
The word so far => fu_
Guess a letter for a 3 letter word =>n

Letters guessed already =>f u n 
Number of wrong guesses =>0
The word so far => fun
Congratulations, you win!!!
noOfPlays 2Do you want to play again? Enter Y to continue and N to exit 
 

If N is selected, the game ends and the statistics of the player is shown as below.
 

Play#	Correct Guesses	Wrong Guesses	Total Guesses
1.0		1.0		6.0		7.0		
2.0		3.0		0.0		3.0		
3.0		1.0		6.0		7.0		
0.0		0.0		0.0		0.0		
0.0		0.0		0.0		0.0		
Total Number of Wrong Guesses 12
Total Number of Correct Guesses 5
Total Number of  Guesses 17
