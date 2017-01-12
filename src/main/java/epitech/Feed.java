package epitech;

/**
 * Created by Julien ANDRE on 05/01/2017.
 */

import static j2html.TagCreator.*;

public class Feed {
    public static Object coucou() {
        return body().with(
                h1("COUCOU").withClass("classe-a-dalas"),
                input().withType("email")
        ).render();
    }
}
