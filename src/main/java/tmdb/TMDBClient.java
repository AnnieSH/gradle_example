package tmdb;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import tmdb.discover.DiscoverClient;
import tmdb.genres.GenresClient;

@Getter
public class TMDBClient {
  private final GenresClient genresClient;
  private final DiscoverClient discoverClient;

  public TMDBClient(String tmdbToken) {
    WebClient webClient =
        WebClient.builder()
            .baseUrl("https://api.themoviedb.org/3")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tmdbToken)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();

    this.genresClient = new GenresClient(webClient);
    this.discoverClient = new DiscoverClient(webClient);
  }
}
