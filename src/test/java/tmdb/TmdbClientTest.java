package tmdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import tmdb.discover.DTO.DiscoverMovieResponse;
import tmdb.discover.DTO.DiscoverMovieResult;
import tmdb.genres.DTO.Genre;
import tmdb.genres.DTO.MovieGenreListResponse;

public class TmdbClientTest extends TmdbClientTestBase {
  @Test
  public void testGetGenres() throws JsonProcessingException {
    MovieGenreListResponse mockResponse =
        MovieGenreListResponse.builder()
            .genres(List.of(new Genre(11, "Drama"), new Genre(123, "Comedy")))
            .build();

    mockServer.enqueue(
        new MockResponse()
            .setBody(objectMapper.writeValueAsString(mockResponse))
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

    MovieGenreListResponse response = tmdbClient.getGenresClient().getMovieGenreList();
    assertEquals(mockResponse, response);
  }

  @Test
  public void testDiscoverMovies() throws JsonProcessingException {
    DiscoverMovieResponse mockResponse =
        DiscoverMovieResponse.builder()
            .page(123)
            .results(List.of(DiscoverMovieResult.builder().title("Steve Candle 3").build()))
            .build();

    mockServer.enqueue(
        new MockResponse()
            .setBody(objectMapper.writeValueAsString(mockResponse))
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

    DiscoverMovieResponse response = tmdbClient.getDiscoverClient().discoverMovies(List.of());
    assertEquals(mockResponse, response);
  }
}
