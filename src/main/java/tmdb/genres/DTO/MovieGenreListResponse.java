package tmdb.genres.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@Jacksonized
public class MovieGenreListResponse {
  private List<Genre> genres;

  public Optional<Integer> getGenreId(String name) {
    for (Genre g : genres) {
      if (g.getName().equals(name)) {
        return Optional.of(g.getId());
      }
    }

    return Optional.empty();
  }

  public Optional<String> getGenreName(Integer id) {
    for (Genre g : genres) {
      if (g.getId() == id) {
        return Optional.of(g.getName());
      }
    }

    return Optional.empty();
  }
}
