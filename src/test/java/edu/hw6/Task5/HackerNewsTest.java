package edu.hw6.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HackerNewsTest {
    @Test
    @DisplayName("Родитель и потомок имеют одинаковый заголовок")
    void test1() {
        // Given
        long parentId = 37570037;
        long kidId = 37571340;

        // When
        String parentTitle = HackerNews.news(parentId);
        String kidTitle = HackerNews.news(kidId);

        // Then
        assertEquals(parentTitle, kidTitle);
    }
}
