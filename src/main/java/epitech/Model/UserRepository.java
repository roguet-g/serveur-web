package epitech.Model;

/**
 * Created by lumy on 19/01/2017.
 */
import java.io.Closeable;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

  /**
   *
   * Basic CRUD operations around {@link Pet}.
   */
  public interface UserRepository extends Closeable {

    /**
     *
     * @return List all pets.
     */
    @SqlQuery("select * from user_rss")
    @MapResultAsBean
    List<User> list();



    ////
    ////
    //// TODO CORRECT ALL FONCTION THAT WAS AN EXAMPLE
    ////
    ////


    /**
     *
     * Find a pet by ID.
     *
     * @param id Pet ID.
     * @return A pet.
     */
    @SqlQuery("select * from pets where id = :id")
    @MapResultAsBean
    User findById(@Bind("id") Integer id);

    /**
     *
     * Insert a new pet.
     *
     * @param pet A pet.
     * @return The generated key.
     */
    @SqlUpdate("insert into pets (name) values (:pet.name)")
    @GetGeneratedKeys
    int insert(@BindBean("pet") User pet);

    /**
     *
     * Update a pet by ID.
     *
     * @param pet Pet to update.
     */
    @SqlUpdate("update pets set name=:pet.name where id=:pet.id")
    void update(@BindBean("pet") User pet);

    /**
     *
     * Delete a pet by ID.
     *
     * @param id Pet ID.
     */
    @SqlUpdate("delete pets where id = :id")
    void deleteById(@Bind("id") Integer id);
  }

