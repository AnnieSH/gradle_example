package tmdb.genres.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class Genre {
  private final int id;
  private final String name;
}
