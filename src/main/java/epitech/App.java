package epitech;

import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  {
    get("/login", () -> "login page");
    get("/", () -> "Hello World!");
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
