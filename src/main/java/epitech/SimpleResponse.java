package epitech;

/**
 * Created by lumy on 21/01/2017.
 */
public class SimpleResponse {
  public int status;

  public SimpleResponse(final int stat) {
    status = stat;
  }
  public String toString() {
    return String.valueOf(status);
  }
}
