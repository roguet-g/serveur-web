package epitech.Model;

import lombok.Data;

import java.util.UUID;

/**
 * Created by Julien ANDRE on 12/01/2017.
 */

@Data
public class User {
    private int     uuid;
    private String  name;
    private String  mail;
    private String  hashPass;
    private UUID    fkeyFeeds;


}
