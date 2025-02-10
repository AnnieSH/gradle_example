package tmdb.genres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Optional;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import tmdb.TmdbClientTestBase;
import tmdb.genres.DTO.Genre;
import tmdb.genres.DTO.MovieGenreListResponse;

@ExtendWith(MockitoExtension.class)
public class GenresTest extends TmdbClientTestBase {
  @Test
  public void testGetGenreId() throws JsonProcessingException {
    MovieGenreListResponse mockResponse =
        MovieGenreListResponse.builder()
            .genres(
                List.of(
                    Genre.builder().id(11).name("Comedy").build(),
                    Genre.builder().id(22).name("Drama").build()))
            .build();
    mockServer.enqueue(
        new MockResponse()
            .setBody(objectMapper.writeValueAsString(mockResponse))
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

    MovieGenreListResponse response = tmdbClient.getGenresClient().getMovieGenreList();
    Optional<Integer> optId = response.getGenreId("Drama");
    assertTrue(optId.isPresent());
    assertEquals(22, optId.get());

    optId = response.getGenreId("Comedy");
    assertTrue(optId.isPresent());
    assertEquals(11, optId.get());

    assertTrue(response.getGenreId("ABCDEFG").isEmpty());
  }

  @Test
  public void testGetGenreName() throws JsonProcessingException {
    MovieGenreListResponse mockResponse =
        MovieGenreListResponse.builder()
            .genres(
                List.of(
                    Genre.builder().id(11).name("Comedy").build(),
                    Genre.builder().id(22).name("Drama").build()))
            .build();
    mockServer.enqueue(
        new MockResponse()
            .setBody(objectMapper.writeValueAsString(mockResponse))
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

    MovieGenreListResponse response = tmdbClient.getGenresClient().getMovieGenreList();
    Optional<String> optId = response.getGenreName(11);
    assertTrue(optId.isPresent());
    assertEquals("Comedy", optId.get());

    optId = response.getGenreName(22);
    assertTrue(optId.isPresent());
    assertEquals("Drama", optId.get());

    assertTrue(response.getGenreName(123456).isEmpty());
  }
}
