package Server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    public static final int PORT = 8078;
    String url = "http://localhost:" + PORT;
    private final String apiToken;
    HttpClient httpClient;
    HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

    public KVTaskClient() throws IOException, InterruptedException {
        URI registerUri = URI.create(url + "/register");
        httpClient = HttpClient.newHttpClient();
        HttpRequest registerRequest = HttpRequest.newBuilder()
                .GET()
                .uri(registerUri)
                .build();
        HttpResponse<String> response = httpClient.send(registerRequest, handler);
        apiToken = response.body();
    }

    protected void put(String key, String json) throws IOException, InterruptedException {
        URI saveUri = URI.create(url + "/save/" + key + "?API_TOKEN=" + apiToken);
        HttpRequest saveRequest = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(saveUri)
                .build();
        HttpResponse<String> response = httpClient.send(saveRequest, handler);
        System.out.println(response.statusCode());
    }

    protected String load(String key) throws IOException, InterruptedException {
        URI loadUri = URI.create(url + "/load/" + key + "?API_TOKEN=" + apiToken);
        HttpRequest loadRequest = HttpRequest.newBuilder()
                .GET()
                .uri(loadUri)
                .build();
        HttpResponse<String> response = httpClient.send(loadRequest, handler);
        System.out.println(response.statusCode());
        return  response.body();
    }
}