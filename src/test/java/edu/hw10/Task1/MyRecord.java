package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public record MyRecord(@Min(1000) int x, @NotNull String string, @NotNull Boolean is) {
}
