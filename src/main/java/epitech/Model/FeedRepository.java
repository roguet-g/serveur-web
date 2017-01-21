package epitech.Model;

/**
 * Created by lumy on 21/01/2017.
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
 * Basic CRUD operations around {@link Feed}.
 */
public interface FeedRepository extends Closeable {
  /**
   *
   * @return List all Feed.
   */
  @SqlQuery("select * from Feed")
  @MapResultAsBean
  List<Feed> list();

  /**
   *
   * Find a Feed by ID.
   *
   * @param id Pet ID.
   * @return A pet.
   */
  @SqlQuery("select * from feed where uid = :uid")
  @MapResultAsBean
  User findById(@Bind("uid") Integer uid);

  /**
   *
   * Insert a new Feed.
   *
   * @param f A feed.
   * @return The generated key.
   */
  @SqlUpdate("insert into feed (name) values (:f.category, f.title, f.url, f.description, f.published)")
  @GetGeneratedKeys
  int insert(@BindBean("f") Feed f);

}
