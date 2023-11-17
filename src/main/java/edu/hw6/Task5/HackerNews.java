package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {

    private static final int TITLE_INDEX_BEGIN = 9;
    private static final int PARENT_INDEX_BEGIN = 9;

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        long[] data = new long[0];
        try (HttpClient client = newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .build();

            HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

            String[] splitResponseBody = response.body().split("[,\\[\\]]");
            int splitSize = splitResponseBody.length;
            data = new long[splitSize - 1];
            for (int i = 1; i < splitSize; i++) {
                data[i - 1] = Long.parseLong(splitResponseBody[i]);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static String news(long id) {
        String newsTitle = null;
        StringBuilder uriId = new StringBuilder();
        uriId.append("https://hacker-news.firebaseio.com/v0/item/").append(id).append(".json");
        try (HttpClient client = newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriId.toString()))
                .build();

            HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

            Pattern pattern = Pattern.compile("(\"title\":\"[\\w* ]*)");
            Matcher matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                newsTitle = matcher.group().substring(TITLE_INDEX_BEGIN);
            } else {
                pattern = Pattern.compile("(\"parent\":\\d+)");
                matcher = pattern.matcher(response.body());
                if (matcher.find()) {
                    newsTitle = news(Long.parseLong(matcher.group().substring(PARENT_INDEX_BEGIN)));
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return newsTitle;
    }
}
