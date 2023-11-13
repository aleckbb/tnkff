package edu.project1;

@SuppressWarnings("uncommentedmain")
public class GameStarter {
    private GameStarter() {
    }

    private static final RandomWordSelector RANDOM_WORD_SELECTOR = new RandomWordSelector();

    public static void main(String[] args) {
        Game game = new Game();
        game.start(RANDOM_WORD_SELECTOR.getRandomWord());
    }
}
