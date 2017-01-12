package epitech;

/**
 * Created by senpaisilver on 12/01/2017.
 */

import lombok.Data;

import static j2html.TagCreator.*;

public @Data class User {
    int _id;
    String _username;
    String _email;

    public User(int id, String username, String email) {
        this._id = id;
        this._username = username;
        this._email = email;
    }

    public static Object getHTML() {
        User usr = new User(0, "TotallyGenericUserName", "user@domain");

        return body().with(
                h1(usr._username)
        ).render();
    }

    public static Object getJSON() {
        return "{}";
    }
}
