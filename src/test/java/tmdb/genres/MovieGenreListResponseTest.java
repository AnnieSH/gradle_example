package tmdb.genres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tmdb.genres.DTO.Genre;
import tmdb.genres.DTO.MovieGenreListResponse;

public class MovieGenreListResponseTest {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void convertJsonToGenre() throws JsonProcessingException {
    String genreJson =
        """
        {
          "id": 28,
          "name": "Action"
        }
        """;

    Genre genre = objectMapper.readValue(genreJson, Genre.class);
    assertNotNull(genre);
    assertEquals(28, genre.getId());
    assertEquals("Action", genre.getName());
  }

  @Test
  public void convertJsonToMovieListResponse() throws JsonProcessingException {
    String genresJson =
        """
        {
          "genres": [
            {
              "id": 28,
              "name": "Action"
            },
            {
              "id": 12,
              "name": "Adventure"
            },
            {
              "id": 16,
              "name": "Animation"
            }
          ]
        }
        """;

    MovieGenreListResponse movieGenreListResponse =
        objectMapper.readValue(genresJson, MovieGenreListResponse.class);
    assertNotNull(movieGenreListResponse);
    assertNotNull(movieGenreListResponse.getGenres());
    assertEquals(3, movieGenreListResponse.getGenres().size());
  }
}
