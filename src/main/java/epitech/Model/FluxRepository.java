package epitech.Model;

import java.io.Closeable;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

/**
 * Created by lumy on 21/01/2017.
 */
  /**
 *
 * Basic CRUD operations around {@link Flux}.
 */
  public interface FluxRepository extends Closeable {
  /**
   *
   * @return List all Flux.
   */
  @SqlQuery("select * from flux")
  @MapResultAsBean
  List<Flux> list();

  /**
   *
   * Find a flux by ID.
   *
   * @param uid flux ID.
   * @return A flux.
   */
  @SqlQuery("select * from flux where uid = :uid")
  @MapResultAsBean
  Flux findById(@Bind("uid") Integer uid);
  /**
   *
   * Find a flux by URL.
   *
   * @param url flux url.
   * @return A flux.
   */
  @SqlQuery("select * from flux where urlFlux = :url or urlSite = :url")
  @MapResultAsBean
  Flux findByUrl(@Bind("url") String url);
  /**
   *
   * Insert a new flux.
   *
   * @param f A flux.
   * @return The generated key.
   */
  @SqlUpdate("insert into flux (title, urlSite, urlFlux, category, description, imageurl) values (:f.title, :f.urlSite, :f.urlFlux, :f.category, :f.description, :f.imageUrl)")
  @GetGeneratedKeys
  int insert(@BindBean("f") Flux f);
  }
