package tmdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TmdbClientTestBase {
  private static final String DUMMY_TOKEN = "DUMMY";
  public static MockWebServer mockServer;

  public TMDBClient tmdbClient;
  public final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeAll
  public static void setup() throws IOException {
    mockServer = new MockWebServer();
    mockServer.start();
  }

  @BeforeEach
  public void beforeEach() {
    String baseUrl = String.format("http://localhost:%d", mockServer.getPort());
    tmdbClient = new TMDBClient(baseUrl, DUMMY_TOKEN);
  }

  @AfterAll
  public static void cleanup() throws IOException {
    mockServer.shutdown();
  }
}
