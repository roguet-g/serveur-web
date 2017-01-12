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
        get("/feed/:from/:count", req -> "GET Feeds "+ req.param("from").intValue() +" index until "+ req.param("from").intValue() +" + "+ req.param("count").intValue());
        get("/feed/:id", req -> "GET Feed " + req.param("id").intValue());
        post("/feed", () -> "POST new feed");
        put("/feed/:id", req -> "PUT feed with " + req.param("id").intValue());
        delete("/feed/:id", req -> "DELETE feed with " + req.param("id").intValue());
        /** Categories **/
        get("/category", () -> "GET Categories");
        get("/category/:id", req -> "GET Categorie with " + req.param("id").intValue());
        put("/category/:id", req -> "PUT Categorie with " + req.param("id").intValue());
        delete("/category/:id", req -> "DELETE Categorie with " + req.param("id").intValue());
        /** User **/
        get("/user", () -> "GET User");
        post("/user", () -> "POST User");
        put("/user", () -> "PUT User");
        delete("/user/:id", req -> "DELETE User with " + req.param("id").intValue());
    }

  public static void main(final String[] args) {
        run(App::new, args);
  }

}
