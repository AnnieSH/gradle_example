package tmdb.genres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tmdb.genres.DTO.Genre;
import tmdb.genres.DTO.MovieGenreListResponse;

@ExtendWith(MockitoExtension.class)
public class GenresTest {
  @Mock GenresClient mockGenresClient;

  @Test
  public void testGetGenresList() {
    MovieGenreListResponse mockResponse =
        new MovieGenreListResponse(
            List.of(
                Genre.builder().id(11).name("Comedy").build(),
                Genre.builder().id(22).name("Drama").build()));
    Mockito.when(mockGenresClient.getMovieGenreList()).thenReturn(mockResponse);

    // Pointless test tbh
    // But can use it as an example later
    MovieGenreListResponse response = mockGenresClient.getMovieGenreList();
    Optional<Integer> optId = response.getGenreId("Drama");
    assertTrue(optId.isPresent());
    assertEquals(22, optId.get());
  }
}
