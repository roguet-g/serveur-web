package epitech;

import epitech.Model.Flux;
import epitech.Model.FluxRepository;
import epitech.Model.UserRepository;
import org.jooby.Jooby;
import org.jooby.Result;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
//import org.pac4j.jwt.profile.JwtGenerator;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.jdbi.Jdbi;
import org.jooby.json.Jackson;
import org.jooby.pac4j.Auth;
import org.pac4j.core.profile.UserProfile;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import com.typesafe.config.Config;

import java.util.List;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    {
        //Database connection
        use(new Jackson());
        use(new Jdbi()
          // 1 dbi ready
          .doWith((DBI dbi, Config conf) -> {
              // 2 open a new handle
              try (Handle handle = dbi.open()) {
                  // 3. execute script
                  handle.execute(conf.getString("schema_user"));
                  handle.execute(conf.getString("schema_flux"));
                  handle.execute(conf.getString("schema_feed"));
              }
          }));

        // Databases Request Example
        get("/db/test", req -> {
            // 1 get dbi and start a new transaction
            return require(DBI.class).inTransaction((handle, status) -> {
                // 2 attach the repository to jdbi handle
                UserRepository repo = handle.attach(UserRepository.class);

                // 3 list all pets
                List<epitech.Model.User> pets = repo.list();
                return pets;
            });
        });


        get("/", (req, rsp) -> rsp.send("Hello World!"));

        get("/test/get_flux", (req, rsp) -> {
            rsp.status(301).type("application/json, application/*+json").send(RomeExample.getFlux(require(DBI.class)));
          });
//            .when("application/json, application/*+json", () ->
//              require(DBI.class).inTransaction((handle, status) -> {
//                try {
//                  System.out.println("Ta Mere ?");
//                  rsp.status(301).type("application/json").send(RomeExample.getFlux(handle));
//                } catch (Throwable throwable) {
//                  throwable.printStackTrace();
//                }
//                System.out.println("Ta Mere ?");
//                return RomeExample.getFlux(handle);
//              }))
//            .when("*", () -> {
//              System.out.println(req.headers());
//              return Status.NOT_ACCEPTABLE;
//            });});

        post("/test/add_flux", req ->  Results
            .when("application/json, application/*+json", () ->
                require(DBI.class).inTransaction((handle, status) -> {
                Flux f = req.body(Flux.class);
                return RomeExample.addFlux(handle, f);
            }))
            .when("*", () -> {
              System.out.println(req.headers());
              return Status.NOT_ACCEPTABLE;
            }));


        get("/test/feeds", req ->
          Results
            .when("application/json, application/*+json", () ->
                require(DBI.class).inTransaction((handle, status) -> {
                  int id = req.param("id").intValue();
                  return RomeExample.syncFlux(handle, id);
                }))
            .when("*", () -> Status.NOT_ACCEPTABLE)
        );

      // Connection
//        get("/connection", () -> "GET Connection");
//        post("/connection", () -> "POST Connection");
//        delete("/connection", () -> "DELETE Connection");
      //  FormClient formClient = new FormClient("/theForm.jsp", new SimpleTestUsernamePasswordAuthenticator(), new UsernameProfileCreator());
        use(new Auth()); // .store(UserRepository.class); //.basic("*", MyUsernamePasswordAuthenticator.class));

        // Examples
        get("/login", () -> "login page");
        get("/coucou", Feed::coucou);
        // Feeds
        get("/allfeeds", () -> "GET All feeds");
        get("/feed/:from/:count", req -> "GET Feeds "+ req.param("from").intValue() +" index until "+ req.param("from").intValue() +" + "+ req.param("count").intValue());
        get("/feed/:id", req -> "GET Feed " + req.param("id").intValue());
        post("/feed", () -> "POST new feed");
        put("/feed/:id", req -> "PUT feed with " + req.param("id").intValue());
        delete("/feed/:id", req -> "DELETE feed with " + req.param("id").intValue());
        // Categories
        get("/category", () -> "GET Categories");
        get("/category/:id", req -> "GET Categorie with " + req.param("id").intValue());
        put("/category/:id", req -> "PUT Categorie with " + req.param("id").intValue());
        delete("/category/:id", req -> "DELETE Categorie with " + req.param("id").intValue());
        // User
        get("/user", () -> Results
                .when("text/html", User::getHTML)
                .when("application/json", User::getJSON)
                .when("*", () -> Status.NOT_ACCEPTABLE)
        );
        post("/user", () -> "POST User");
        put("/user", () -> "PUT User");
        delete("/user/:id", req -> "DELETE User with " + req.param("id").intValue());

    }

  public static void main(final String[] args) {
        run(App::new, args);
  }



}
