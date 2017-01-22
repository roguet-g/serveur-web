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
  Feed findById(@Bind("uid") Integer uid);

  /**
   *
   * Insert a new Feed.
   *
   * @param f A feed.
   * @return The generated key.
   */
  @SqlUpdate("insert into feed (title, category, urlFeed, urlFlux, description, content, published, author) values" +
    "(:f.title, :f.category, :f.urlFeed, :f.urlFlux, :f.description, :f.content, :f.published, :f.author)")
  @GetGeneratedKeys
  int insert(@BindBean("f") Feed f);

  @SqlQuery("select * from feed where urlFeed = :feed.urlFeed")
  @MapResultAsBean
  Feed findByUrl(@BindBean("feed") Feed feed);

  @SqlQuery("select * from feed where urlFlux = :urlFlux")
  @MapResultAsBean
  List<Feed> allByUrl(@Bind("urlFlux") String urlFlux);
}
