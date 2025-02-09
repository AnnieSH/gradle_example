package tmdb.genres;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import tmdb.genres.DTO.MovieGenreListResponse;

@RequiredArgsConstructor
public class GenresClient {
  private static final String MOVIE_LIST_URI = "/genre/movie/list";

  private final WebClient webClient;

  public MovieGenreListResponse getMovieGenreList() {
    return webClient
        .get()
        .uri(MOVIE_LIST_URI)
        .retrieve()
        .bodyToMono(MovieGenreListResponse.class)
        .block();
  }
}
