package epitech.Model;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.Date;

/**
 * Created by lumy on 21/01/2017.
 */
public class Feed {
  private Integer uid;
  private Integer category;
  private String title;
  private String urlFeed;
  private String urlFlux;
  private String description;
  private String content;
  private Date   published;
  private String  author;

  // Should be saving that
  // entry.getCategories()
  // Saving one or the other entry.getLink() || entry.getUri()
//  System.out.println("Author: " + entry.getTitle());
//        System.out.println("Publish Date: " + entry.getPublishedDate());
//        System.out.println("Description: " + entry.getDescription().getValue());
//        System.out.println("Content: " + entry.getContents());

  public Feed(String urlFlux, SyndEntry entry) {
    setTitle(entry.getTitle());
    setUrlFeed(entry.getLink());
    setDescription(entry.getDescription().getValue());
    setContent(entry.getContents().get(0).toString());
    setPublished(entry.getPublishedDate());
    setAuthor(entry.getAuthor());
    setUrlFlux(urlFlux);
  }
  public Feed() {

  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getPublished() {
    return published;
  }

  public void setPublished(Date published) {
    this.published = published;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getUrlFeed() {
    return urlFeed;
  }

  public void setUrlFeed(String urlFeed) {
    this.urlFeed = urlFeed;
  }

  public String getUrlFlux() {
    return urlFlux;
  }

  public void setUrlFlux(String urlFlux) {
    this.urlFlux = urlFlux;
  }
}
