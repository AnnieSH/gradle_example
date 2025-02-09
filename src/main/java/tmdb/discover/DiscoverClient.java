package tmdb.discover;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import tmdb.discover.DTO.DiscoverMovieResponse;

@RequiredArgsConstructor
public class DiscoverClient {
  private static final String PATH_DISCOVER_MOVIES = "/discover/movie";

  private static final String PARAM_WITH_GENRES = "with_genres";
  private static final String PARAM_PAGE = "page";

  private final WebClient client;

  public DiscoverMovieResponse discoverMovies(List<Integer> genres) {
    return client
        .get()
        .uri(
            uriBuilder ->
                uriBuilder.path(PATH_DISCOVER_MOVIES).queryParam(PARAM_WITH_GENRES, genres).build())
        .retrieve()
        .bodyToMono(DiscoverMovieResponse.class)
        .block();
  }

  public DiscoverMovieResponse discoverMovies(int page, List<Integer> genres) {
    return client
        .get()
        .uri(
            uriBuilder ->
                uriBuilder
                    .path(PATH_DISCOVER_MOVIES)
                    .queryParam(PARAM_PAGE, page)
                    .queryParam(PARAM_WITH_GENRES, genres)
                    .build())
        .retrieve()
        .bodyToMono(DiscoverMovieResponse.class)
        .block();
  }
}
