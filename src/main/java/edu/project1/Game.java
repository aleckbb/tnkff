package edu.project1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("RegexpSinglelineJava")
public class Game {
    private final static int NEG_ONE = -1;
    private final static int MAX_ATTEMPTS = 5;
    private final RandomWordSelector randomWordSelector = new RandomWordSelector();
    private final WordMaskOperator wordMaskOperator = new WordMaskOperator();
    private final Scanner scanner;
    private ByteArrayOutputStream byteArrayOutputStream;
    private int mistakesCount;
    private String letter;

    public Game() {
        scanner = new Scanner(System.in);
    }

    public Game(String in) {
        scanner = new Scanner(in);
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteArrayOutputStream);
        System.setOut(stream);
    }

    public String getOut() {
        return byteArrayOutputStream.toString();
    }

    private void input() {
        boolean fl = true;
        do {
            letter = scanner.nextLine();
            if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
                fl = false;
            } else {
                if (letter.equals("giveUp")) {
                    mistakesCount = NEG_ONE;
                    letter = " ";
                    return;
                }
                System.out.print("> Input correct letter:\n< ");
            }
        } while (fl);
    }

    private String inputChoice() {
        String result;
        do {
            result = scanner.nextLine();
            if (result.length() == 1 && (result.equals("N") || result.equals("E"))) {
                return result;
            } else {
                System.out.print("> Input correct choice:\n< ");
            }
        } while (true);
    }

    private boolean win(int numberGuessLetter, Set<String> wordUniqueLetters) {
        return numberGuessLetter == wordUniqueLetters.size();
    }

    public boolean start(String guessedWord) {
        if (!guessedWord.isEmpty()) {
            boolean fl = false;
            mistakesCount = 0;
            wordMaskOperator.clearBuffer();
            wordMaskOperator.setWord(guessedWord);
            System.out.print("> A word has been guessed!\n> If you want to give up, enter - giveUp\n");

            wordMaskOperator.printMask();
            while (!win(wordMaskOperator.getNumberGuessLetter(), wordMaskOperator.getWordUniqueLetters())) {
                System.out.print("\n> Guess a letter: \n< ");
                input();
                if (mistakesCount == NEG_ONE) {
                    System.out.print("\n> You give up!");
                    fl = true;
                    break;
                }
                if (wordMaskOperator.isLetterBeUsed(letter)) {
                    System.out.print(">\n> This letter is already by used!");
                } else {
                    wordMaskOperator.inputLetterInSet(letter);
                    if (wordMaskOperator.checkLetterInSet(letter)) {
                        System.out.print("> Hit!\n");
                        wordMaskOperator.updateMask(letter);
                        wordMaskOperator.printMask();
                    } else {
                        mistakesCount++;
                        System.out.print("> Missed, mistake " + mistakesCount + " out of "
                            + MAX_ATTEMPTS + ".\n");
                        wordMaskOperator.printMask();
                    }
                }
                if (mistakesCount == MAX_ATTEMPTS) {
                    System.out.print("\n> You lost!");
                    fl = true;
                    break;
                }
            }
            if (!fl) {
                System.out.print("\n> You won!");
            }
            System.out.print("\n> Menu: [N]ew game/ [E]xit\n< ");
            String choice = inputChoice();
            if (choice.equals("N")) {
                return start(randomWordSelector.getRandomWord());
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
