package edu.project1;

import java.util.Random;

public class RandomWordSelector {
    private final String[] words =
        {"butterfly", "carousel", "umbrella", "guitar", "sunshine", "adventure", "chocolate", "ocean", "whisper",
            "firefly"
        };

    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
