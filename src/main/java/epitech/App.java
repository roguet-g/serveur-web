package epitech;

import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    {
        /** Examples **/
        get("/login", () -> "login page");
        get("/", () -> "Hello World!");
        get("/coucou", Feed::coucou);
        /** Connection **/
        get("/connection", () -> "GET Connection");
        post("/connection", () -> "POST Connection");
        delete("/connection", () -> "DELETE Connection");
        /** Feeds **/
        get("/allfeeds", () -> "GET All feeds");
        get("/feed/:from/:count", () -> "GET Feeds :from index until :from + :count");
        get("/feed/:id", () -> "GET Feed :id");
        post("/feed", () -> "POST new feed");
        put("/feed/:id", () -> "PUT feed with :id");
        delete("/feed/:id", () -> "DELETE feed with :id");
        /** Categories **/
        get("/category", () -> "GET Categories");
        get("/category/:id", () -> "GET Categorie with :id");
        put("/category/:id", () -> "PUT Categorie with :id");
        delete("/category/:id", () -> "DELETE Categorie with :id");
        /** User **/
        get("/user", () -> "GET User");
        post("/user", () -> "POST User");
        put("/user", () -> "PUT User");
        delete("/user/:id", () -> "DELETE User with :id");
    }

  public static void main(final String[] args) {
        run(App::new, args);
  }

}
