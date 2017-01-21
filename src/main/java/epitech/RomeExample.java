package epitech;

/**
 * Created by lumy on 21/01/2017.
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import epitech.Model.FeedRepository;
import epitech.Model.Flux;
import epitech.Model.FluxRepository;
import epitech.Model.UserRepository;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class RomeExample {

  public static List<Flux> getFlux(Handle handle) {
    FluxRepository repo = handle.attach(FluxRepository.class);
    // 3 list all pets
    List<epitech.Model.Flux> f = repo.list();
    return f;
  }
  public static SimpleResponse addFlux(Handle handle, Flux f) {
    FluxRepository repo = handle.attach(FluxRepository.class);
    if (null != repo.findByUrl(f.getUrl())) {
      return new SimpleResponse(1);
    }
    repo.insert(f);
    // 3 list all pets
    return new SimpleResponse(0);
  }

  public static String test() throws Exception {
    URL url = new URL("http://korben.info/feed"); //http://www.lemonde.fr/rss/une.xml"); //http://www.ywh.me/rss"); // korben.info/feed");
    HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
    httpcon.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
    // Reading the feed
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed = input.build(new XmlReader(httpcon));
    List entries = feed.getEntries();
    Iterator itEntries = entries.iterator();

    while (itEntries.hasNext()) {
      SyndEntry entry = (SyndEntry) itEntries.next();
      System.out.println("Title: " + entry.getTitle() + " - " + entry.getCategories());
      System.out.println("Link: " + entry.getLink() + " - " + entry.getContents());
      System.out.println("Author: " + entry.getAuthor());

      System.out.println("Publish Date: " + entry.getPublishedDate());
      System.out.println("Description: " + entry.getDescription().getValue());
      System.out.println();
    }
    return "Hello Test";
  }
}
