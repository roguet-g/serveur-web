package epitech;

/**
 * Created by senpaisilver on 12/01/2017.
 */

import com.google.gson.Gson;
import lombok.Data;

import static j2html.TagCreator.*;

public @Data class User {
    int id;
    String username;
    String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static Object getHTML() {
        User usr = new User(0, "TotallyGenericUserName", "user@domain");

        return body().with(
                h1(usr.username)
        ).render();
    }

    public static Object getJSON() {
        User usr = new User(0, "TotallyGenericUserName", "user@domain");
        Gson gson = new Gson();

        return gson.toJson(usr);
    }
}
