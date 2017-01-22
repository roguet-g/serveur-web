package epitech;

/**
 * Created by lumy on 21/01/2017.
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import epitech.Model.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.xml.sax.InputSource;

public class RomeExample {

  public static FluxList getFlux(DBI dbi) {
   return dbi.inTransaction((handle, status) -> {
      // 2 attach the repository to jdbi handle
     FluxRepository repo = handle.attach(FluxRepository.class);

      // 3 list all pets
      List<Flux> pets = repo.list();
     FluxList ret = new FluxList();
     ret.setFeeds(pets);
      return ret;
  });
  }

  public static Flux addFlux(Handle handle, Flux f) {
    System.out.println("Received Flux " + f);
    FluxRepository repo = handle.attach(FluxRepository.class);
    Flux check = null;
    if (null != (check = repo.findByUrl(f.getUrlFlux())))
      return check;
    SyndFeed r = null;
    if (null == (r = sync_flux(f)))
      return f;
    f = new Flux(f.getUrlFlux(), r);
    int key = repo.insert(f);
    f.setUid(key);
    return f;
  }
  public static SyndFeed sync_flux(Flux f) {
    try {
      URL url = new URL(f.getUrlFlux()); //http://www.lemonde.fr/rss/une.xml"); //http://www.ywh.me/rss"); // korben.info/feed");
      HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
      httpcon.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(httpcon));
      return feed;

    } catch (Exception e) {
      System.out.println("EXCEPTIOOOOON");
      e.printStackTrace();
      return null;
    }
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

  public static Object syncFlux(Handle handle, int uid) throws IOException, FeedException {
    FluxRepository repo = handle.attach(FluxRepository.class);
    FeedRepository feedRepo = handle.attach(FeedRepository.class);
    Flux f = repo.findById(uid);

    System.out.println("URLL: " + f.getUrlFlux());
    try (CloseableHttpClient client = HttpClients.createMinimal()) {
      HttpUriRequest method = new HttpGet(f.getUrlFlux());
      try (CloseableHttpResponse response = client.execute(method);
           InputStream stream = response.getEntity().getContent()) {
        SyndFeedInput input = new SyndFeedInput();
        XmlReader xmlr = new XmlReader(stream);
        SyndFeed sFeed = input.build(xmlr);

        List entries = sFeed.getEntries();
        Iterator itEntries = entries.iterator();
        while (itEntries.hasNext()) {
          SyndEntry entry = (SyndEntry) itEntries.next();
          epitech.Model.Feed feed = new epitech.Model.Feed(f.getUrlFlux(), entry);
          if (null  == feedRepo.findByUrl(feed))
            feedRepo.insert(feed);
        }
      }
    }
    FeedList ret = new FeedList();
    ret.setFeeds( feedRepo.allByUrl(f.getUrlFlux()));
    return ret;
  }
}
