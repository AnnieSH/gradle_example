import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import tmdb.TMDBClient;
import tmdb.discover.DTO.DiscoverMovieResponse;
import tmdb.discover.DTO.DiscoverMovieResult;
import tmdb.discover.DiscoverClient;
import tmdb.genres.DTO.Genre;
import tmdb.genres.GenresClient;

public class Main {
  // Script to get top 25 popular movies of each genre
  // Printed out in a readable format
  // And written to json
  private static final int NUM_RESULTS = 25;
  private static final String JSON_OUTPUT_PATH = "topMoviesPerGenre.json";

  public static void main(String... args) throws IOException {
    String tmdbToken =
        Optional.ofNullable(System.getenv("TMDB_TOKEN"))
            .orElseThrow(() -> new IllegalStateException("No TMDB token found"));

    TMDBClient tmdbClient = new TMDBClient(tmdbToken);

    GenresClient genresClient = tmdbClient.getGenresClient();
    DiscoverClient discoverClient = tmdbClient.getDiscoverClient();

    List<Genre> genres = genresClient.getMovieGenreList().getGenres();

    Map<Genre, List<DiscoverMovieResult>> discoveredMovies = new HashMap<>();

    for (Genre g : genres) {
      int page = 1;
      ArrayList<DiscoverMovieResult> results = new ArrayList<>(NUM_RESULTS);

      do {
        DiscoverMovieResponse discoverResponse =
            discoverClient.discoverMovies(page, List.of(g.getId()));
        List<DiscoverMovieResult> discoveredMoviesResults = discoverResponse.getResults();

        if (results.size() + discoveredMoviesResults.size() > NUM_RESULTS) {
          discoveredMoviesResults =
              discoveredMoviesResults.subList(0, NUM_RESULTS - results.size());
        }

        results.addAll(discoveredMoviesResults);
        page++;
      } while (results.size() < NUM_RESULTS);

      discoveredMovies.put(g, results);
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String moviesJson = objectMapper.writer().writeValueAsString(discoveredMovies);

    discoveredMovies.forEach(
        (genre, movies) -> {
          System.out.printf("\n[%s]%n", genre.getName());
          String movieListString =
              movies.stream()
                  .map(DiscoverMovieResult::getTitle)
                  .reduce("Movies:", (m1, m2) -> String.format("%s\n- %s", m1, m2));
          System.out.println(movieListString);
        });

    Files.write(Paths.get(JSON_OUTPUT_PATH), moviesJson.getBytes());
  }
}
