package epitech.Model;

import lombok.Data;

import java.util.UUID;

/**
 * Created by Julien ANDRE on 12/01/2017.
 */

//@Data
public class User {
    private Integer id;
    private String  name;
//    private String  mail;
//    private String  hashPass;
//    private UUID    fkeyFeeds;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
